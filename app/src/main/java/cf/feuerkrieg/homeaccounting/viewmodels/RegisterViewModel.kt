package cf.feuerkrieg.homeaccounting.viewmodels

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cf.feuerkrieg.homeaccounting.models.User
import cf.feuerkrieg.homeaccounting.repositories.UserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel: ViewModel() {

    private val userRepo = UserRepo()

    var fio = ""
        set(value) {
            if(!TextUtils.isEmpty(value))
                fioError.value = ""
            field = value
        }

    var login = ""
        set(value) {
            if(!TextUtils.isEmpty(value))
                loginError.value = ""
            field = value
        }

    var password = ""
        set(value) {
            if(!TextUtils.isEmpty(value) &&
                !value.contains(':'))
                passwordError.value = ""
            field = value
        }

    val fioError = MutableLiveData<String>("")
    val loginError = MutableLiveData<String>("")
    val passwordError = MutableLiveData<String>("")

    val isRequesting = MutableLiveData<Boolean>(false)


    val hasException = MutableLiveData<Boolean>(false)
    val exception = MutableLiveData<String>()

    val user = User()

    var registerSuccesfull: (() -> Unit)? = null

    fun registerUser() {

        hasException.value = false

        user.fio = fio
        user.login = login
        user.password = password

        var hasError = false

        if(TextUtils.isEmpty(user.fio)) {
            fioError.value = "Это поле обязательно"
            hasError = true
        }

        if(TextUtils.isEmpty(user.login)) {
            loginError.value = "Это поле обязательно"
            hasError = true
        }

        if(TextUtils.isEmpty(user.password)) {
            passwordError.value = "Это поле обязательно"
            hasError = true
        }

        if(user.password.contains(':')) {
            passwordError.value = "Пароль не может содерржать ':'"
            hasError = true
        }

        if(hasError)
            return

        isRequesting.value = true

        viewModelScope.launch(Dispatchers.IO) {

            try {
                userRepo.register(user)

                withContext(Dispatchers.Main) {
                    registerSuccesfull?.invoke()
                }
            }
            catch (ex: Exception) {
                hasException.postValue(true)
                exception.postValue(ex.message)
            }
            finally {
                isRequesting.postValue(false)
            }
        }

    }



}