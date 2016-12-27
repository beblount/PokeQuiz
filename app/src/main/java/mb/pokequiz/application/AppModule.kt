package mb.pokequiz.application

import dagger.Module
import dagger.Provides
import mb.pokequiz.application.PokeApplication
import mb.pokequiz.data.repository.Database
import mb.pokequiz.data.repository.PokeApi
import mb.pokequiz.data.repository.PokeRepository
import mb.pokequiz.data.repository.Repository
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Module(includes = arrayOf(WebModule::class, PersistenceModule::class))
class AppModule(var application: PokeApplication) {

    @Singleton
    @Provides
    fun application() : PokeApplication {
        return application;
    }

    @Provides
    @Singleton
    fun repository(database: Database, pokeApi: PokeApi) : PokeRepository {
        return Repository(database, pokeApi)
    }
}