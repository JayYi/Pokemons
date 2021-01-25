package com.jay.pokemons.data.repository

import com.jay.pokemons.data.remote.PokemonRemoteDataSource
import com.jay.pokemons.model.PokeMonListResponse

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
): PokemonRepository {

    override suspend fun getPokemonList(offset: Int?, limit: Int?): PokeMonListResponse =
        pokemonRemoteDataSource.getPokemonList(offset, limit)

}