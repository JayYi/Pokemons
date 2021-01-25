package com.jay.pokemons.data.local

import com.jay.pokemons.data.database.PokemonDatabase
import com.jay.pokemons.data.entity.PokemonFavoriteEntity
import javax.inject.Inject

class PokemonLocalDataSourceImpl @Inject constructor(
    private val pokemonDatabase: PokemonDatabase
) : PokemonLocalDataSource {

    override suspend fun insertMyFavoritePokemon(pokemonFavoriteEntity: PokemonFavoriteEntity) {
        pokemonDatabase.pokemonFavoriteDao().insert(pokemonFavoriteEntity)
    }

    override suspend fun findMyFavoritePokemonById(pokemonId: Int): Int? =
        pokemonDatabase.pokemonFavoriteDao().getFavoritePokemonId(pokemonId)

    override suspend fun deleteMyFavoritePokemon(pokemonId: Int) {
        pokemonDatabase.pokemonFavoriteDao().deleteFavoritePokemonById(pokemonId)
    }
}