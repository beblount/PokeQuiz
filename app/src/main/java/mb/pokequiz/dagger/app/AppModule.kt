package mb.pokequiz.dagger.app

import dagger.Module
import dagger.Provides
import mb.pokequiz.PokeApplication
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


}