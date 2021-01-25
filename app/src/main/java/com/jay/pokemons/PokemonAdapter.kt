package com.jay.pokemons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jay.pokemons.databinding.ListItemPokemonBinding
import com.jay.pokemons.model.Pokemon
import java.lang.Exception
import javax.inject.Inject

class PokemonAdapter @Inject constructor() : PagedListAdapter<Pokemon, PokemonHolder>(
    PokemonDiffUtil()
) {
    var clickEvent: ((Pokemon) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonHolder {
        val binding = ListItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonHolder(binding).apply {
            itemView.setOnClickListener {
                val item = getItem(adapterPosition) ?: return@setOnClickListener
                clickEvent?.invoke(item)
            }
        }
    }


    override fun onBindViewHolder(holder: PokemonHolder, position: Int) {
        val item = getItem(position) ?: throw Exception("no item")
        holder.bind(item)
    }
}

class PokemonHolder(
    val binding: ListItemPokemonBinding
): RecyclerView.ViewHolder(binding.root) {

    fun bind(pokemon: Pokemon) {
        with(binding) {
            this.pokemon = pokemon
            executePendingBindings()
        }
    }
}

class PokemonDiffUtil : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem && oldItem.url == newItem.url
    }

}