package com.jay.pokemons.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon_favorite")
data class PokemonFavoriteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "pokemon_id")
    val pokemonId: Int,
    @ColumnInfo(name = "name")
    val name: String
)