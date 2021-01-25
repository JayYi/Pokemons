package com.jay.pokemons.util

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.jay.pokemons.PokemonAdapter
import com.jay.pokemons.model.Pokemon

@BindingAdapter("bind:pokemon_items")
fun RecyclerView.setPokemonItems(items: PagedList<Pokemon>? = null) {
    val adapter = this.adapter as? PokemonAdapter
    adapter?.submitList(items)
}