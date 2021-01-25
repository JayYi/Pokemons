package com.jay.pokemons

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.paginglibraryexample.data.remote.PokemonRemoteDataSourceImpl
import com.jay.pokemons.data.repository.PokemonRepositoryImpl
import com.jay.pokemons.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val repository by lazy {
        PokemonRepositoryImpl(PokemonRemoteDataSourceImpl)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = viewModel
        binding.lifecycleOwner = this
    }
}