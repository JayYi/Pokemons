package com.jay.pokemons.network

import com.jay.pokemons.model.PokeMonListResponse
import com.jay.pokemons.model.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("pokemon/")
    suspend fun getPokemonList(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): PokeMonListResponse

    @GET("pokemon/{pid}")
    suspend fun getPokemon(
        @Path("pid") pid: Int
    ): PokemonResponse

}
