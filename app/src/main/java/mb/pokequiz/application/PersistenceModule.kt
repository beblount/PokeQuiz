package mb.pokequiz.application

import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import mb.pokequiz.data.repository.poke.*
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Module
class PersistenceModule {

    init {
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
    }

    @Provides
    @Singleton
    fun pokeDatabase() : PokeDatabase {
        return RealmRepository()
    }

    @Provides
    @Singleton
    fun repository(pokeDatabase: PokeDatabase, pokeApi: PokeApi) : PokeRepository {
        return Repository(pokeDatabase, pokeApi)
    }

}
