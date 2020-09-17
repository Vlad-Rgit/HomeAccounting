package cf.feuerkrieg.homeaccounting.security

import android.content.Context
import android.os.Build
import android.security.KeyPairGeneratorSpec
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import cf.feuerkrieg.homeaccounting.HomeApplication
import java.math.BigInteger
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.PrivateKey
import java.security.spec.AlgorithmParameterSpec
import java.util.*
import javax.crypto.Cipher
import javax.security.auth.x500.X500Principal

class AuthHelper {

    private val transformation = "RSA/ECB/PKCS1Padding"

    private val passwordKey = "HomeSecret"
    private val loginKey = "HomeLogin"

    private val appContext = HomeApplication.getAppContext()

    private val preferences = appContext
        .getSharedPreferences("HomeApp", Context.MODE_PRIVATE)

    private val alias = "HomeAccSecret"

    private val keyStore = KeyStore.getInstance("AndroidKeyStore")

    init {

        keyStore.load(null)
        val spec: AlgorithmParameterSpec

        if(!keyStore.containsAlias(alias)) {

            val startDate = Calendar.getInstance()
            val endDate  = Calendar.getInstance()
            endDate.add(Calendar.YEAR, 1)

            //Generate spec for different versions of api
            if(Build.VERSION.SDK_INT < 23) {
                spec = KeyPairGeneratorSpec.Builder(appContext)
                    .setAlias(alias)
                    .setSubject(X500Principal("CN=$alias"))
                    .setSerialNumber(BigInteger.valueOf(1337))
                    .setStartDate(startDate.time)
                    .setEndDate(endDate.time)
                    .build()
            }
            else {
                spec = KeyGenParameterSpec.Builder(
                    alias,
                    KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
                )
                    .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                    .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
                    .build()
            }

            val keyGenerator = KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore"
            )

            keyGenerator.initialize(spec)
            keyGenerator.generateKeyPair()
        }
    }

    fun hasAuthenticated(): Boolean {
        return preferences.contains(loginKey) &&
                preferences.contains(passwordKey)
    }

    fun clearPassword() {
        preferences.edit()
            .remove(passwordKey)
            .apply()
    }

    fun savePassword(password: String) {

        //Encrypt text
        val publicKey = keyStore.getCertificate(alias).publicKey
        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encrypted = cipher.doFinal(password.toByteArray(Charsets.UTF_8))
        val value = Base64.encodeToString(encrypted, Base64.DEFAULT)


        //Save it to shared preferences
        preferences.edit()
            .putString(passwordKey, value)
            .apply()
    }

    fun saveLogin(login: String) {
        preferences.edit()
            .putString(loginKey, login)
            .apply()
    }

    fun getLogin(): String {
        return preferences.getString(loginKey, "")!!
    }

    fun clearLogin() {
        preferences.edit()
            .remove(loginKey)
            .apply()
    }

    fun clearAuthData(){
        clearLogin()
        clearPassword()
    }

    fun getPassword(): String {

        //Get the encrypted password
        val encryptedText = preferences.getString(passwordKey, "")

        //Decrypt the text
        val privateKey = keyStore.getKey(alias, null)
                as PrivateKey

        val cipher = Cipher.getInstance(transformation)
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decrypted = cipher.doFinal(Base64.decode(encryptedText, Base64.DEFAULT))

        return String(decrypted, Charsets.UTF_8)
    }
}