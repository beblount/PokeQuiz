package mb.pokequiz.quiz

import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.mvp.MvpLoadingView

/**
 * Created by mbpeele on 12/27/16.
 */
interface QuizView : MvpLoadingView {

    fun onPokemonReceived(pokemon: Pokemon)
}