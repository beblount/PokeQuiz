package mb.pokequiz.home

import mb.pokequiz.base.mvp.MvpView
import mb.pokequiz.data.model.Pokemon

/**
 * Created by mbpeele on 12/27/16.
 */
interface HomeView : MvpView {

    fun onPokemonReceive(pokemon: Pokemon)
}