package com.jay.pokemons.network

import com.jay.pokemons.model.PokemonResponse

object Api {

    private val apiService by lazy {
        ApiClient.apiService
    }

    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null) =
        apiService.getPokemonList(offset, limit)

    suspend fun getPokemon(pid: Int): PokemonResponse =
        apiService.getPokemon(pid)

}