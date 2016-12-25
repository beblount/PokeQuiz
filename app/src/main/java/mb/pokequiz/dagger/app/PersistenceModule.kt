package mb.pokequiz.dagger.app

import dagger.Module
import io.realm.Realm
import io.realm.RealmConfiguration
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

}
