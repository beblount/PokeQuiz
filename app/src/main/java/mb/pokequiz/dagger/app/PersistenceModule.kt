package mb.pokequiz.dagger.app

import dagger.Module
import dagger.Provides
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun realm() : RealmConfiguration {
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)
        return config
    }
}
