package com.jay.pokemons.di

import android.util.Log
import com.jay.pokemons.BuildConfig
import com.jay.pokemons.network.ApiService
import com.jay.pokemons.util.peekBody
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    private const val baseApiUrl = "https://pokeapi.co/api/v2/"

    private const val READ_TIMEOUT_SECONDS = 30
    private const val WRITE_TIMEOUT_SECONDS = 10
    private const val CONNECTION_TIMEOUT_SECONDS = 30


    @Singleton
    @Provides
    fun provideRetrofit(builder: OkHttpClient.Builder): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseApiUrl)
            .client(builder.build())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun providerOkHttpClientBuilder(interceptor: Interceptor):  OkHttpClient.Builder =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(READ_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)
            .connectTimeout(CONNECTION_TIMEOUT_SECONDS.toLong(), TimeUnit.SECONDS)

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create()

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .build()
            ).also { response ->
                if (BuildConfig.DEBUG) {
                    val statusCode = response.code()
                    if (statusCode == 200) {
                        Log.i("ApiClient", "res=${response}, body=${response.peekBody()}")
                    } else {
                        Log.e("ApiClient", "res=${response}, body=${response.peekBody()}")
                    }
                }
            }
        }
    }
}