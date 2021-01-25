package com.jay.pokemons.data.repository

import com.jay.pokemons.data.entity.PokemonFavoriteEntity
import com.jay.pokemons.model.PokeMonListResponse

interface PokemonRepository {
    suspend fun getPokemonList(offset: Int? = null, limit: Int? = null): PokeMonListResponse

    suspend fun insertFavoritePokemon(pokemonId: Int, pokemonName: String)

    suspend fun findFavoritePokemonId(pokemonId: Int): Int?

    suspend fun deleteFavoritePokemonById(pokemonId: Int)
}