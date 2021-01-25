package com.jay.pokemons.data.repository

import com.jay.pokemons.model.PokeMonListResponse
import com.jay.pokemons.model.PokemonResponse

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null): PokeMonListResponse

    suspend fun getPokemon(pokemonId: Int): PokemonResponse

    suspend fun insertFavoritePokemon(pokemonId: Int, pokemonName: String)

    suspend fun findFavoritePokemonId(pokemonId: Int): Int?

    suspend fun deleteFavoritePokemonById(pokemonId: Int)
}