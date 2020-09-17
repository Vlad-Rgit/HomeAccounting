package cf.feuerkrieg.homeaccounting.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cf.feuerkrieg.homeaccounting.repositories.UserRepo
import cf.feuerkrieg.homeaccounting.security.AuthHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class LoginViewModel: ViewModel() {

    private val userRepo = UserRepo()

    val hasError = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val isRequesting = MutableLiveData<Boolean>()


    var userLogin: String = ""
    var userPassword: String = ""

    var loginSuccesful: (() -> Unit)? = null

    fun loginUser() {

        hasError.value = false
        isRequesting.value = true


        viewModelScope.launch(Dispatchers.IO) {

            try {

                userRepo.login()

                withContext(Dispatchers.Main) {
                    val authHelper = AuthHelper()
                    authHelper.savePassword(userPassword)
                    authHelper.saveLogin(userLogin)
                    loginSuccesful?.invoke()
                }
            }
            catch (ex: IOException) {
                hasError.postValue(true)
                error.postValue("Не удалось подключиться к серверу\n" +
                        "Проверьте интернет соединение")
            }
            catch (ex: Exception) {
                hasError.postValue(true)
                error.postValue(ex.message)
            }
            finally {
                isRequesting.postValue(false)
            }
        }
    }

}