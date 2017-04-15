package mb.pokequiz.db

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.annotations.RealmModule

/**
 * Created by mbpeele on 4/15/17.
 */
@RealmModule(library = true, allClasses = true)
class Database {

    companion object {

        private val SCHEMA_VERSION = 1L
        private val SCHEMA_NAME = "PokeQuiz.Realm"
        internal lateinit var realmConfiguration : io.realm.RealmConfiguration

        @JvmStatic
        fun init(context: android.content.Context) {
            Realm.init(context)

            realmConfiguration = RealmConfiguration.Builder()
                    .name(SCHEMA_NAME)
                    .schemaVersion(SCHEMA_VERSION)
                    .deleteRealmIfMigrationNeeded()
                    .modules(Database())
                    .build()
        }
    }
}