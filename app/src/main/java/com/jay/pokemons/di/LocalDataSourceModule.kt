package com.jay.pokemons.di

import com.jay.pokemons.data.local.PokemonLocalDataSource
import com.jay.pokemons.data.local.PokemonLocalDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Singleton
    @Binds
    abstract fun provideLocalDataResource(pokemonLocalDataSourceImpl: PokemonLocalDataSourceImpl): PokemonLocalDataSource

}