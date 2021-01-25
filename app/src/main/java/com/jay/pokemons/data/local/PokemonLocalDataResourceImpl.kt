package com.jay.pokemons.data.local

import android.content.Context
import com.jay.pokemons.MainApplication
import com.jay.pokemons.data.database.PokemonDatabase
import com.jay.pokemons.data.entity.PokemonFavoriteEntity

object PokemonLocalDataSourceImpl: PokemonLocalDataSource  {

    private val context: Context by lazy {
        MainApplication.instance
    }

    private val pokemonDatabase: PokemonDatabase by lazy {
        PokemonDatabase.getInstance(context)
    }

    override suspend fun insertMyFavoritePokemon(pokemonFavoriteEntity: PokemonFavoriteEntity) {
        pokemonDatabase.pokemonFavoriteDao().insert(pokemonFavoriteEntity)
    }

    override suspend fun findMyFavoritePokemonById(pokemonId: Int): Int? =
        pokemonDatabase.pokemonFavoriteDao().getFavoritePokemonId(pokemonId)

    override suspend fun deleteMyFavoritePokemon(pokemonId: Int) {
        pokemonDatabase.pokemonFavoriteDao().deleteFavoritePokemonById(pokemonId)
    }
}