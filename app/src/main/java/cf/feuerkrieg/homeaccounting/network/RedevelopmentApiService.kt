package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.models.Redevelopment
import retrofit2.http.GET

interface RedevelopmentApiService {
    @GET("redevelopments")
    suspend fun getAll(): List<Redevelopment>
}