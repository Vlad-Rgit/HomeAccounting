package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.models.WindowType
import retrofit2.http.GET

interface WindowTypeApiService {
    @GET("windowTypes")
    suspend fun getWindowTypes(): List<WindowType>
}