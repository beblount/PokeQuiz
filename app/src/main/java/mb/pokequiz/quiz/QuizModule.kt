package mb.pokequiz.quiz

import dagger.Module
import dagger.Provides
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.data.repository.poke.PokeRepository

/**
 * Created by mbpeele on 12/27/16.
 */
@Module
@ActivityScope
class QuizModule {

    @Provides
    @ActivityScope
    fun presenter(repository: PokeRepository) : QuizPresenter {
        return QuizPresenter(repository)
    }

}