package cf.feuerkrieg.homeaccounting.network



import cf.feuerkrieg.homeaccounting.models.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface UsersService {

    @GET("users")
    suspend fun getUsers(): List<User>

    @GET("login")
    suspend fun loginUser(): Response<Unit>

    @POST("register")
    suspend fun registerUser(@Body user: User)
}