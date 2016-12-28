package mb.pokequiz.home

import mb.pokequiz.mvp.MvpLoadingView
import mb.pokequiz.data.model.Pokemon

/**
 * Created by mbpeele on 12/27/16.
 */
interface HomeView : MvpLoadingView {

    fun onPokemonReceive(pokemon: Pokemon)
}