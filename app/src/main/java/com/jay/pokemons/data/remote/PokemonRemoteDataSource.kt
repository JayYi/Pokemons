package com.jay.pokemons.data.remote

import com.jay.pokemons.model.PokeMonListResponse
import com.jay.pokemons.model.PokemonResponse

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null): PokeMonListResponse

    suspend fun getPokemon(pokemonId: Int): PokemonResponse

}