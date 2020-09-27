package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.models.ReasonAbcenseVentilation
import retrofit2.http.GET

interface ReasonAbcenseVentilationApiService {

    @GET("reasonAbcenseVentilations")
    suspend fun getAll(): List<ReasonAbcenseVentilation>

}