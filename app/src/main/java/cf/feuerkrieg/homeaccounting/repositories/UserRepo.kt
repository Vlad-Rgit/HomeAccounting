package cf.feuerkrieg.homeaccounting.repositories

import cf.feuerkrieg.homeaccounting.models.User
import cf.feuerkrieg.homeaccounting.network.usersApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepo {

    suspend fun login(){
        withContext(Dispatchers.IO) {

            val response = usersApiService.loginUser()
            if(response.code() == 401) {
                throw Exception("Неправильный логин или пароль")
            }
        }
    }

    suspend fun register(user: User) {
        withContext(Dispatchers.IO) {
            usersApiService.registerUser(user)
        }
    }
}