package cf.feuerkrieg.homeaccounting.network

import cf.feuerkrieg.homeaccounting.models.Flat
import retrofit2.http.GET
import retrofit2.http.Path

interface FlatApiService {

    @GET("flats/{homeId}")
    suspend fun getFlatsByHome(@Path("homeId")
                                   homeId: Int): List<Flat>



}