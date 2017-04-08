package mb.pokequiz.quiz

import dagger.Module
import dagger.Provides
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.data.repository.PokeRepository
import mb.pokequiz.domain.web.PokeApi

/**
 * Created by mbpeele on 12/27/16.
 */
@Module
@ActivityScope
class QuizModule {

    @Provides
    fun presenter(pokeApi: PokeApi) : QuizPresenter {
        val repository = PokeRepository(pokeApi)
        return QuizPresenter(repository)
    }

}