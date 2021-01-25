package com.jay.pokemons.data.repository

import com.jay.pokemons.data.entity.PokemonFavoriteEntity
import com.jay.pokemons.data.local.PokemonLocalDataSource
import com.jay.pokemons.data.remote.PokemonRemoteDataSource
import com.jay.pokemons.model.PokeMonListResponse
import com.jay.pokemons.model.PokemonResponse

class PokemonRepositoryImpl(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonLocalDataSource: PokemonLocalDataSource
) : PokemonRepository {

    override suspend fun getPokemonList(offset: Int?, limit: Int?): PokeMonListResponse =
        pokemonRemoteDataSource.getPokemonList(offset, limit)

    override suspend fun getPokemon(pokemonId: Int): PokemonResponse =
        pokemonRemoteDataSource.getPokemon(pokemonId)

    override suspend fun insertFavoritePokemon(pokemonId: Int, pokemonName: String) {
        pokemonLocalDataSource.insertMyFavoritePokemon(
            PokemonFavoriteEntity(
                pokemonId = pokemonId,
                name = pokemonName
            )
        )
    }

    override suspend fun findFavoritePokemonId(pokemonId: Int): Int? =
        pokemonLocalDataSource.findMyFavoritePokemonById(pokemonId)

    override suspend fun deleteFavoritePokemonById(pokemonId: Int) {
        pokemonLocalDataSource.deleteMyFavoritePokemon(pokemonId)
    }

}