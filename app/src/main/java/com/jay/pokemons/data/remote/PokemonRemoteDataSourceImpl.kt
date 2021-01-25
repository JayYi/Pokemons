package com.jay.paginglibraryexample.data.remote

import com.jay.pokemons.model.PokeMonListResponse
import com.jay.pokemons.network.Api

class PokemonRemoteDataSourceImpl : PokemonRemoteDataSource {

    private val api by lazy {
        Api
    }

    override suspend fun getPokemonList(offset: Int?, limit: Int?): PokeMonListResponse =
        api.getPokemonList(offset, limit)
}