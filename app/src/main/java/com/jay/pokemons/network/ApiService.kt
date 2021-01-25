package com.jay.pokemons.network

import com.jay.pokemons.model.PokeMonListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): PokeMonListResponse

}
