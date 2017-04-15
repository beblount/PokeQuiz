package mb.pokequiz.pokemon

import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.mvp.MvpView

/**
 * Created by mbpeele on 12/29/16.
 */
interface PokemonView : MvpView {

    fun onPokemonReceived(pokemon: Pokemon)

}