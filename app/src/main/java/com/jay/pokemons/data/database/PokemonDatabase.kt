package com.jay.pokemons.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jay.pokemons.data.dao.PokemonFavoriteDao
import com.jay.pokemons.data.entity.PokemonFavoriteEntity

@Database(
    entities = [PokemonFavoriteEntity::class],
    version = 1
)

abstract class PokemonDatabase : RoomDatabase() {
    abstract fun pokemonFavoriteDao(): PokemonFavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: PokemonDatabase? = null

        fun getInstance(context: Context): PokemonDatabase =
            INSTANCE ?: synchronized(this) {
                buildDatabase(context)
                    .also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PokemonDatabase::class.java,
                "pokemon.db"
            )
                .build()
    }
}