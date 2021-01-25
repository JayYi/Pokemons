package com.jay.pokemons.network

object Api {

    private val apiService by lazy {
        ApiClient.apiService
    }

    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null) =
        apiService.getPokemonList(offset, limit)

}