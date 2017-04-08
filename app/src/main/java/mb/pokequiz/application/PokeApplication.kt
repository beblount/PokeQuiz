package mb.pokequiz.application

import android.app.Application
import io.realm.Realm
import io.realm.RealmConfiguration

/**
 * Created by mbpeele on 2/1/16.
 */
class PokeApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)
        val config = RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build()
        Realm.setDefaultConfiguration(config)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .webModule(WebModule())
                .build()
    }


}
