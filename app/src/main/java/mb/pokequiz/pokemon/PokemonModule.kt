package mb.pokequiz.pokemon

import dagger.Module
import dagger.Provides
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.presentation.mvp.SchedulerProvider
import mb.pokequiz.presentation.pokemon.PokemonPresenter

/**
 * Created by mbpeele on 12/29/16.
 */
@ActivityScope
@Module
class PokemonModule {

    @Provides
    @ActivityScope
    fun presenter(pokeApi: PokeApi, schedulerProvider: SchedulerProvider) : PokemonPresenter {
        return PokemonPresenter(pokeApi, schedulerProvider)
    }
}