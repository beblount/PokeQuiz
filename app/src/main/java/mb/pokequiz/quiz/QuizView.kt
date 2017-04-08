package mb.pokequiz.quiz

import mb.pokequiz.domain.model.Pokemon
import mb.pokequiz.mvp.MvpView

/**
 * Created by mbpeele on 12/27/16.
 */
interface QuizView : MvpView {

    fun onPokemonReceived(pokemon: Pokemon)

    fun showError()

    fun showLoading()

}