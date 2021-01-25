package com.jay.pokemons.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jay.pokemons.data.entity.PokemonFavoriteEntity

@Dao
interface PokemonFavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemonFavoriteEntity: PokemonFavoriteEntity)

    @Query("SELECT * from pokemon_favorite")
    suspend fun getAllFavoritePokemon(): List<PokemonFavoriteEntity>

    @Query("SELECT pokemon_id from pokemon_favorite where pokemon_id = :pokemonId")
    suspend fun getFavoritePokemonId(pokemonId: Int): Int?

    @Query("DELETE FROM pokemon_favorite where pokemon_id = :pokemonId")
    suspend fun deleteFavoritePokemonById(pokemonId: Int)
}