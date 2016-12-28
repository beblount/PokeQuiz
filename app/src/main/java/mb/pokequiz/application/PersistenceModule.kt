package mb.pokequiz.application

import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.mappers.PokeMapperFactory
import mb.pokequiz.data.repository.poke.PokeDatabase
import mb.pokequiz.data.repository.poke.RealmRepository
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Module
class PersistenceModule() {

    init {
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }

    @Provides
    @Singleton
    fun factory() : MapperFactory {
        return PokeMapperFactory()
    }

    @Provides
    @Singleton
    fun repository(factory: MapperFactory) : PokeDatabase {
        return RealmRepository(factory)
    }

}
