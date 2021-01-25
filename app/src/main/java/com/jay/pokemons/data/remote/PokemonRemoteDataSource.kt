package com.jay.pokemons.data.remote

import com.jay.pokemons.model.PokeMonListResponse

interface PokemonRemoteDataSource {

    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null): PokeMonListResponse

}