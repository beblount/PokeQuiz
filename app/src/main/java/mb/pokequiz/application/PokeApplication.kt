package mb.pokequiz.application

import android.app.Application
import io.realm.Realm

/**
 * Created by mbpeele on 2/1/16.
 */
class PokeApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .persistenceModule(PersistenceModule())
                .webModule(WebModule())
                .build()
    }


}
