package com.jay.pokemons.data.local

import com.jay.pokemons.data.entity.PokemonFavoriteEntity

interface PokemonLocalDataSource {

    suspend fun insertMyFavoritePokemon(pokemonFavoriteEntity: PokemonFavoriteEntity)

    suspend fun findMyFavoritePokemonById(pokemonId: Int): Int?

    suspend fun deleteMyFavoritePokemon(pokemonId: Int)

}