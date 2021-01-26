package com.jay.pokemons.di

import com.jay.pokemons.data.repository.PokemonRepository
import com.jay.pokemons.data.repository.PokemonRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRepository(
        pokemonRepositoryImpl: PokemonRepositoryImpl
    ): PokemonRepository
}