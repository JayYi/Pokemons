package com.jay.pokemons.model

import android.net.Uri

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