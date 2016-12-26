package mb.pokequiz.dagger.app

import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.mappers.PokeMapperFactory
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

}
