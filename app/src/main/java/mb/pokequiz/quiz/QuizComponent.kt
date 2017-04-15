package mb.pokequiz.quiz

import dagger.Component
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.application.AppComponent
import mb.pokequiz.presentation.quiz.QuizPresenter

/**
 * Created by mbpeele on 12/27/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(QuizModule::class))
interface QuizComponent {

    fun presenter() : QuizPresenter

}