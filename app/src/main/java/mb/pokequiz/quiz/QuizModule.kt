package mb.pokequiz.quiz

import dagger.Module
import dagger.Provides
import mb.pokequiz.application.ActivityScope
import peele.miles.db.repository.PokeRepository

/**
 * Created by mbpeele on 12/27/16.
 */
@Module
@ActivityScope
class QuizModule {

    @Provides
    fun presenter(pokeRepository: PokeRepository) : QuizPresenter {
        return QuizPresenter(pokeRepository)
    }

}