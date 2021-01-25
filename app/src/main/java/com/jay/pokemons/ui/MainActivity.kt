package com.jay.pokemons.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jay.pokemons.R
import com.jay.pokemons.databinding.ActivityMainBinding
import com.jay.pokemons.ui.pokemoncard.PokemonCardFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var adapter: PokemonAdapter

    private var cardDialog: PokemonCardFragment? = null

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