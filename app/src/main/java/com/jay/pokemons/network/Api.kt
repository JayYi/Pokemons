package com.jay.pokemons.network

import com.jay.pokemons.model.PokemonResponse
import javax.inject.Inject

class Api @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null) =
        apiService.getPokemonList(offset, limit)

    suspend fun getPokemon(pid: Int): PokemonResponse =
        apiService.getPokemon(pid)

}