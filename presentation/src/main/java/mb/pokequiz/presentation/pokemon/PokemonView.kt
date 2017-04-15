package mb.pokequiz.presentation.pokemon

import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.presentation.mvp.MvpView

/**
 * Created by mbpeele on 12/29/16.
 */
interface PokemonView : MvpView {

    fun onPokemonReceived(pokemon: Pokemon)

}