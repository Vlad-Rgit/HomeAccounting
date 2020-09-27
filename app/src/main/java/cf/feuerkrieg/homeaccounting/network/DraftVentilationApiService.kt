package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.models.DraftVentilation
import retrofit2.http.GET


interface DraftVentilationApiService {

    @GET("draftVentilation")
    suspend fun getAll(): List<DraftVentilation>

}