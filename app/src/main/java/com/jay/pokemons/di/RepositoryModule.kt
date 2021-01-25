package com.jay.pokemons.di

import com.jay.pokemons.data.local.PokemonLocalDataSource
import com.jay.pokemons.data.remote.PokemonRemoteDataSource
import com.jay.pokemons.data.repository.PokemonRepository
import com.jay.pokemons.data.repository.PokemonRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        localDataSource: PokemonLocalDataSource,
        remoteDataSource: PokemonRemoteDataSource
    ): PokemonRepository =
        PokemonRepositoryImpl(
            pokemonRemoteDataSource = remoteDataSource,
            pokemonLocalDataSource = localDataSource
        )
}