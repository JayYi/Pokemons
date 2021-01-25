package com.jay.pokemons.util.paging

import android.net.Uri
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.jay.pokemons.data.repository.PokemonRepository
import com.jay.pokemons.model.Pokemon
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class PokemonDataSource(
    private val repository: PokemonRepository,
    private val scope: CoroutineScope
) : PageKeyedDataSource<String, Pokemon>() {

    override fun loadInitial(
        params: LoadInitialParams<String>,
        callback: LoadInitialCallback<String, Pokemon>
    ) {
        try {
            scope.launch {
                val pokes = repository.getPokemonList()
                callback.onResult(pokes.results, pokes.previous, pokes.next)
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "error=${e.message}")
        }
    }

    override fun loadAfter(
        params: LoadParams<String>,
        callback: LoadCallback<String, Pokemon>
    ) {
        try {
            scope.launch {
                val (offset, limit) = getOffsetAndLimit(params.key)
                val pokes = repository.getPokemonList(
                    offset = offset,
                    limit = limit
                )
                callback.onResult(pokes.results, pokes.next)
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "error=${e.message}")
        }
    }

    override fun loadBefore(
        params: LoadParams<String>,
        callback: LoadCallback<String, Pokemon>
    ) {
        try {
            scope.launch {
                val (offset, limit) = getOffsetAndLimit(params.key)
                val pokes = repository.getPokemonList(
                    offset = offset,
                    limit = limit
                )
                callback.onResult(pokes.results, pokes.previous)
            }
        } catch (e: Exception) {
            Log.e(LOG_TAG, "error=${e.message}")
        }
    }

    private fun getOffsetAndLimit(url: String): Pair<Int?, Int?> {
        val uri = Uri.parse(url)
        val offset = uri.getQueryParameter("offset")
        val limit = uri.getQueryParameter("limit")
        return offset?.toInt() to limit?.toInt()
    }

    companion object {
        private const val LOG_TAG = "PokemonDataSource"
    }

}
