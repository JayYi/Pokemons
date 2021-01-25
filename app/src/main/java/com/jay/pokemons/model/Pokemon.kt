package com.jay.pokemons.model

import android.net.Uri
import com.squareup.moshi.Json

data class Pokemon(
    val url: String,
    val name: String
) {
    val pid: Int
        get() {
            val paths = Uri.parse(url).pathSegments
            return paths.last().toInt()
        }
}

data class PokeMonListResponse(
    val count: Int,
    val previous: String,
    val next: String,
    val results: List<Pokemon>
)

data class PokemonResponse(
    val species: Species,
    val sprites: Sprites,
    val abilities: List<Ability>,
    val stats: List<Stats>
) {
    data class Species(
        val name: String
    )

    data class Sprites(
        @field:Json(name = "front_default") val frontDefault: String
    )

    data class Ability(
        @field:Json(name = "ability") val info: AbilityInfo,
        @field:Json(name = "is_hidden") val hidden: Boolean,
        val slot: Int
    )

    data class AbilityInfo(
        val name: String,
        val url: String
    )


    data class Stats(
        @field:Json(name = "base_stat") val baseStat: Int,
        val effort: Int,
        val stat: Stat
    )

    data class Stat(
        val name: String,
        val url: String
    )
}
