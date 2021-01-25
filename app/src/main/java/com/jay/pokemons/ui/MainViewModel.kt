package com.jay.pokemons.ui

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.jay.pokemons.data.repository.PokemonRepository
import com.jay.pokemons.model.Pokemon
import com.jay.pokemons.util.paging.PokemonDataSourceFactory

class MainViewModel @ViewModelInject constructor(
    private val repository: PokemonRepository
) : ViewModel() {
    private val config: PagedList.Config = PagedList.Config.Builder()
        .setInitialLoadSizeHint(20)
        .setPageSize(10)
        .setPrefetchDistance(10)
        .setEnablePlaceholders(true)
        .build()

    private val factory by lazy {
        PokemonDataSourceFactory(
            repository,
            viewModelScope
        )
    }

    val data: LiveData<PagedList<Pokemon>> = LivePagedListBuilder<String, Pokemon>(
        factory, config
    ).build()

    fun init() {
        data.value?.dataSource?.invalidate()
    }
}