package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.models.AccessToVentilation
import retrofit2.http.GET

interface AccessToVentilationApiService {

    @GET("accessToVentilations")
    suspend fun getAll(): List<AccessToVentilation>
}