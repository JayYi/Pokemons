package com.jay.pokemons.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.pokemons.R
import com.jay.pokemons.data.remote.PokemonRemoteDataSourceImpl
import com.jay.pokemons.data.repository.PokemonRepositoryImpl
import com.jay.pokemons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(repository)
    }

    private lateinit var binding: ActivityMainBinding

    private val repository by lazy {
        PokemonRepositoryImpl(PokemonRemoteDataSourceImpl)
    }

    private val adapter by lazy {
        PokemonAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_main
        )

        binding.list.adapter = adapter.apply {
            clickEvent = {

            }
        }

        binding.vm = viewModel
        binding.lifecycleOwner = this

        viewModel.init()
    }
}