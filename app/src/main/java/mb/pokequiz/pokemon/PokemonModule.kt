package mb.pokequiz.pokemon

import dagger.Module
import dagger.Provides
import mb.pokequiz.application.ActivityScope
import peele.miles.db.repository.PokeRepository

/**
 * Created by mbpeele on 12/29/16.
 */
@ActivityScope
@Module
class PokemonModule {

    @Provides
    @ActivityScope
    fun presenter(pokeRepository: PokeRepository) : PokemonPresenter {
        return PokemonPresenter(pokeRepository)
    }
}