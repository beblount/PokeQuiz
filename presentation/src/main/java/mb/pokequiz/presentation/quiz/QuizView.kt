package mb.pokequiz.presentation.quiz

import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.presentation.mvp.MvpView

/**
 * Created by mbpeele on 12/27/16.
 */
interface QuizView : MvpView {

    fun onPokemonReceived(pokemon: Pokemon)

    fun showError()

    fun showLoading()

}