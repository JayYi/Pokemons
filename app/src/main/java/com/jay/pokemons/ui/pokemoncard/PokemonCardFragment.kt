package com.jay.pokemons.ui.pokemoncard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.jay.pokemons.R
import com.jay.pokemons.data.local.PokemonLocalDataSourceImpl
import com.jay.pokemons.data.remote.PokemonRemoteDataSourceImpl
import com.jay.pokemons.data.repository.PokemonRepositoryImpl
import com.jay.pokemons.databinding.FragmentPokemonCardBinding

class PokemonCardFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentPokemonCardBinding
    // bundle 사용하는 걸 추천
    private var pokemonId: Int = 0

    private val viewModel: PokemonCardViewModel by viewModels {
        PokemonCardViewModelFactory(
            PokemonRepositoryImpl(
                pokemonRemoteDataSource = PokemonRemoteDataSourceImpl,
                pokemonLocalDataSource = PokemonLocalDataSourceImpl
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_pokemon_card, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.vm = viewModel
        binding.pokemonId = pokemonId
        binding.lifecycleOwner = this

        viewModel.load(pokemonId)
    }

    companion object {
        fun newInstance(pokemonId: Int) =
            PokemonCardFragment().apply {
                this.pokemonId = pokemonId
            }

        const val LOG_TAG = "PokemonCard"
        const val TAG = "pokemon_card"
    }
}