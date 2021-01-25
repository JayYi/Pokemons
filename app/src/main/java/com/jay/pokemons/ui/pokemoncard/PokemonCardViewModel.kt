package com.jay.pokemons.ui.pokemoncard

import androidx.lifecycle.*
import com.jay.pokemons.data.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokemonCardViewModel (
    private val repository: PokemonRepository
) : ViewModel() {
    private val _imageUrl: MutableLiveData<String> = MutableLiveData()
    val imageUrl: LiveData<String>
        get() = _imageUrl

    private val _abilities: MutableLiveData<String> = MutableLiveData()
    val abilities: LiveData<String>
        get() = _abilities

    val name: MutableLiveData<String> = MutableLiveData()
    val favoriteChecked: MutableLiveData<Boolean> = MutableLiveData()

    fun load(pokemonId: Int) {
        viewModelScope.launch {
            val pokemon = repository.getPokemon(pokemonId)
            val abilities = pokemon.abilities.joinToString { it.info.name }
            val favoritePokemonId = repository.findFavoritePokemonId(pokemonId)
            _abilities.value = abilities
            _imageUrl.value = pokemon.sprites.frontDefault
            favoriteChecked.value = favoritePokemonId != null && pokemonId == favoritePokemonId
            name.value = pokemon.species.name
        }
    }

    fun onFavoriteChecked(pokemonId: Int?, name: String, checked: Boolean) {
        val id = pokemonId ?: return
        viewModelScope.launch {
            if (checked) {
                repository.insertFavoritePokemon(
                    pokemonId = id,
                    pokemonName = name
                )
            } else {
                repository.deleteFavoritePokemonById(id)
            }
        }
    }
}

class PokemonCardViewModelFactory(
    private val repository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(PokemonCardViewModel::class.java)) {
            PokemonCardViewModel(repository) as T
        } else {
            throw IllegalArgumentException()
        }
    }
}