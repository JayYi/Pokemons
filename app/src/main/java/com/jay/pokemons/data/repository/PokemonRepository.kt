package com.jay.pokemons.data.repository

import com.jay.pokemons.model.PokeMonListResponse

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null): PokeMonListResponse
}