package cf.feuerkrieg.homeaccounting.network


import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private val jsonFactory = Json.asConverterFactory(
    MediaType.get("application/json"))

private val okHttpClient = OkHttpClient.Builder()
    .addInterceptor(BasicAuthInterceptor())
    .build()

private val retrofit =
    Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("http://192.168.88.237:2500/api/")
        .addConverterFactory(jsonFactory)
        .build()

val usersApiService: UsersService by lazy {
    retrofit.create(UsersService::class.java)
}

val flatApiService: FlatApiService by lazy {
    retrofit.create(FlatApiService::class.java)
}

val homeApiService: HomeApiService by lazy {
    retrofit.create(HomeApiService::class.java)
}

