package mb.pokequiz.dagger.activity

import dagger.Module
import dagger.Provides
import mb.pokequiz.dagger.ActivityScope
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.repository.PokeApi
import mb.pokequiz.data.repository.PokeRepository
import mb.pokequiz.data.repository.RealmRepository
import mb.pokequiz.data.repository.Repository

/**
 * Created by mbpeele on 12/25/16.
 */
@Module
@ActivityScope
class ActivityModule {

    @Provides
    @ActivityScope
    fun repository(api: PokeApi, factory: MapperFactory) : PokeRepository {
        val localRepository = RealmRepository(factory)
        return Repository(localRepository, api)
    }
}