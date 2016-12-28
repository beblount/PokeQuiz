package mb.pokequiz.application

import dagger.Module
import dagger.Provides
import mb.pokequiz.data.repository.poke.PokeApi
import mb.pokequiz.data.repository.poke.PokeDatabase
import mb.pokequiz.data.repository.poke.PokeRepository
import mb.pokequiz.data.repository.poke.Repository
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
    fun repository(pokeDatabase: PokeDatabase, pokeApi: PokeApi) : PokeRepository {
        return Repository(pokeDatabase, pokeApi)
    }
}