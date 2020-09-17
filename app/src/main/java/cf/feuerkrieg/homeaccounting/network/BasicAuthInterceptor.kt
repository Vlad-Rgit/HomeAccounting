package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.security.AuthHelper
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response

class BasicAuthInterceptor
    : Interceptor {

    private lateinit var login: String
    private lateinit var password: String
    private val authHelper = AuthHelper()

    override fun intercept(chain: Interceptor.Chain): Response {

        var request = chain.request()

        if(authHelper.hasAuthenticated()) {

            if(!::login.isInitialized)
                login = authHelper.getLogin()
            if(!::password.isInitialized)
                password = authHelper.getPassword()

            val credentials = Credentials.basic(login, password)

            request = request.newBuilder()
                .header("Authorization", credentials)
                .build()
        }

        return chain.proceed(request)
    }
}