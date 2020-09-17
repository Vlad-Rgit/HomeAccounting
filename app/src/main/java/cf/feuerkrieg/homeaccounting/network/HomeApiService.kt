package cf.feuerkrieg.homeaccounting.network


import cf.feuerkrieg.homeaccounting.models.Home
import retrofit2.http.GET

interface HomeApiService {

    @GET("homes")
    suspend fun getHomes(): List<Home>

}