package com.jay.pokemons.di

import com.jay.pokemons.data.database.PokemonDatabase
import com.jay.pokemons.data.local.PokemonLocalDataSource
import com.jay.pokemons.data.local.PokemonLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideLocalDataResource(pokemonDatabase: PokemonDatabase): PokemonLocalDataSource =
        PokemonLocalDataSourceImpl(pokemonDatabase)

}