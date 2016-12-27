package mb.pokequiz.home

import dagger.Module
import dagger.Provides
import mb.pokequiz.activity.ActivityScope
import mb.pokequiz.data.repository.PokeRepository

/**
 * Created by mbpeele on 12/27/16.
 */
@Module
@ActivityScope
class HomeModule {

    @Provides
    @ActivityScope
    fun presenter(repository: PokeRepository) : HomePresenter {
        return HomePresenter(repository)
    }

}