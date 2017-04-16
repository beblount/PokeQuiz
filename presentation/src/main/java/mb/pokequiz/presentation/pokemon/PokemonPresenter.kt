package mb.pokequiz.presentation.pokemon

import io.reactivex.functions.Consumer
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.presentation.mvp.BasePresenter
import mb.pokequiz.presentation.mvp.SchedulerProvider
import mb.pokequiz.utils.length

/**
 * Created by mbpeele on 12/29/16.
 */
class PokemonPresenter(val pokeApi: PokeApi, schedulerProvider: SchedulerProvider) : BasePresenter<PokemonView>(schedulerProvider) {

    fun getPokemon(id: Int) {
        pokeApi.getPokemonById(id)
                .observeOn(schedulerProvider.ui())
                .subscribe(Consumer {
                    get()?.onPokemonReceived(it)
                })
    }

    fun formatPokemonName(pokemon: Pokemon) : String {
        val name = pokemon.name.capitalize()
        val orderString = "#" + "0".repeat(3 - pokemon.order.length()) + pokemon.order
        return String.format("%s %s", name, orderString)
    }
}