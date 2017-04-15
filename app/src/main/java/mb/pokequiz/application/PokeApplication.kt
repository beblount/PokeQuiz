package mb.pokequiz.application

import android.app.Application
import mb.pokequiz.db.Database

/**
 * Created by mbpeele on 2/1/16.
 */
class PokeApplication : Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        mb.pokequiz.db.Database.init(this)

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .webModule(WebModule())
                .build()
    }


}
