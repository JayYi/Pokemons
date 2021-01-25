package com.jay.pokemons.util.paging

import androidx.paging.DataSource
import com.jay.pokemons.data.repository.PokemonRepository
import com.jay.pokemons.model.Pokemon
import kotlinx.coroutines.CoroutineScope

class PokemonDataSourceFactory (
    private val repository: PokemonRepository,
    private val scope: CoroutineScope
) : DataSource.Factory<String, Pokemon>() {

    override fun create(): DataSource<String, Pokemon> {
        return PokemonDataSource(repository, scope)
    }

}