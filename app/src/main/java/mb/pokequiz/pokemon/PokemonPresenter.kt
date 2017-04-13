package mb.pokequiz.pokemon

import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.mvp.BasePresenter
import peele.miles.db.repository.PokeRepository

/**
 * Created by mbpeele on 12/29/16.
 */
class PokemonPresenter(val pokeRepository: PokeRepository) : BasePresenter<PokemonView>() {

    fun getPokemon(id: Int) : Pokemon {
        return pokeRepository.getPokemonById(id).blockingGet()
    }

    fun formatPokemonName(pokemon: Pokemon) : String {
        val name = pokemon.name.capitalize()
        val order = "#" + pokemon.order
        return String.format("%s %s", name, order)
    }
}