package com.jay.pokemons.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jay.pokemons.data.dao.PokemonFavoriteDao
import com.jay.pokemons.data.entity.PokemonFavoriteEntity

@Database(
    entities = [PokemonFavoriteEntity::class],
    version = 1
)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonFavoriteDao(): PokemonFavoriteDao
}