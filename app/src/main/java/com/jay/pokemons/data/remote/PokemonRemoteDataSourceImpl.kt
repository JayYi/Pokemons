package com.jay.pokemons.data.remote

import com.jay.pokemons.model.PokeMonListResponse
import com.jay.pokemons.model.PokemonResponse
import com.jay.pokemons.network.Api
import javax.inject.Inject

class PokemonRemoteDataSourceImpl @Inject constructor(
    private val api: Api
) : PokemonRemoteDataSource {

    override suspend fun getPokemonList(offset: Int?, limit: Int?): PokeMonListResponse =
        api.getPokemonList(offset, limit)

    override suspend fun getPokemon(pokemonId: Int): PokemonResponse =
        api.getPokemon(pokemonId)
}