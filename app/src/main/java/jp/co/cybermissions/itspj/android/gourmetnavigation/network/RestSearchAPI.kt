package jp.co.cybermissions.itspj.android.gourmetnavigation.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

/** レストラン検索API URL */
private const val BASE_URL = "https://api.gnavi.co.jp/"
private const val KEY_ID = "e54eb6eb2bf72c6e0b385205579ba0f4"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface RestSearchAPIService {
    @GET("RestSearchAPI/v3/")
    //
    suspend fun getRests(
        @Query("name") name: String,
        @Query("keyid") keyid: String = KEY_ID
    ): List<Rest>
}

object RestSearchAPI {
    val retrofitService : RestSearchAPIService by lazy {
        retrofit.create(RestSearchAPIService::class.java)
    }
}