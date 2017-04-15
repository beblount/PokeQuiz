package mb.pokequiz.quiz

import dagger.Module
import dagger.Provides
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.presentation.mvp.SchedulerProvider
import mb.pokequiz.presentation.quiz.QuizPresenter

/**
 * Created by mbpeele on 12/27/16.
 */
@Module
@ActivityScope
class QuizModule {

    @Provides
    fun presenter(pokeApi: PokeApi, schedulerProvider: SchedulerProvider) : QuizPresenter {
        return QuizPresenter(pokeApi, schedulerProvider)
    }

}