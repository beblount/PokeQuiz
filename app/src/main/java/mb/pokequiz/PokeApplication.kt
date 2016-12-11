package mb.pokequiz

import android.app.Application
import io.realm.Realm
import mb.pokequiz.dagger.app.*

/**
 * Created by mbpeele on 2/1/16.
 */
class PokeApplication : Application() {

    companion object {
        @JvmStatic lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Realm.init(this)

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .persistenceModule(PersistenceModule())
                .webModule(WebModule())
                .build()
    }


}
