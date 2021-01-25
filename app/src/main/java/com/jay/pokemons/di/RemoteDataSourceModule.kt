package com.jay.pokemons.di

import com.jay.pokemons.data.remote.PokemonRemoteDataSource
import com.jay.pokemons.data.remote.PokemonRemoteDataSourceImpl
import com.jay.pokemons.network.Api
import com.jay.pokemons.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideRemoteDataSource(api: Api): PokemonRemoteDataSource =
        PokemonRemoteDataSourceImpl(api)


    @Singleton
    @Provides
    fun provideApi(apiService: ApiService): Api =
        Api(apiService)

}