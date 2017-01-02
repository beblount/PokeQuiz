package mb.pokequiz.pokemon

import dagger.Module
import dagger.Provides
import mb.pokequiz.application.ActivityScope

/**
 * Created by mbpeele on 12/29/16.
 */
@ActivityScope
@Module
class PokemonModule {

    @Provides
    @ActivityScope
    fun presenter() : PokemonPresenter {
        return PokemonPresenter()
    }
}