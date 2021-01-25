package com.jay.pokemons.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.pokemons.R
import com.jay.pokemons.data.local.PokemonLocalDataSourceImpl
import com.jay.pokemons.data.remote.PokemonRemoteDataSourceImpl
import com.jay.pokemons.data.repository.PokemonRepositoryImpl
import com.jay.pokemons.databinding.ActivityMainBinding
import com.jay.pokemons.ui.pokemoncard.PokemonCardFragment

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(repository)
    }

    private lateinit var binding: ActivityMainBinding

    private var cardDialog: PokemonCardFragment? = null

    private val repository by lazy {
        PokemonRepositoryImpl(
            pokemonRemoteDataSource = PokemonRemoteDataSourceImpl,
            pokemonLocalDataSource = PokemonLocalDataSourceImpl
        )
    }

    private val adapter by lazy {
        PokemonAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )

        binding.list.adapter = adapter.apply {
            clickEvent = {
                cardDialog = PokemonCardFragment.newInstance(
                    it.pid
                )
                    .also { dialog ->
                        dialog.show(
                            supportFragmentManager,
                            PokemonCardFragment.TAG
                        )
                    }
            }
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.init()
    }

    override fun onDestroy() {
        cardDialog?.dismissAllowingStateLoss()
        super.onDestroy()
    }
}