package com.jay.pokemons.network

import android.util.Log
import com.jay.pokemons.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.io.IOException
import java.util.concurrent.TimeUnit

object ApiClient {
    private const val READ_TIMEOUT_SECONDS = 30
    private const val WRITE_TIMEOUT_SECONDS = 10
    private const val CONNECTION_TIMEOUT_SECONDS = 30

    private const val baseApiUrl = "https://pokeapi.co/api/v2/"

    val apiService: ApiService = getRetrofit(baseApiUrl).create()

    private fun getRetrofit(baseUrl: String): Retrofit {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)


        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(builder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }
}

internal class HeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder().build()
        val response = chain.proceed(request)
        val statusCode = response.code()
        if (BuildConfig.DEBUG) {
            if (statusCode == 200) {
                Log.i("ApiClient", "res=${response}, body=${response.peekBody()}")
            } else {
                Log.e("ApiClient", "res=${response}, body=${response.peekBody()}")
            }

        }
        return response
    }
}
