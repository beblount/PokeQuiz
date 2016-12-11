package mb.pokequiz.dagger.app

import dagger.Component
import mb.pokequiz.ui.base.BaseActivity
import mb.pokequiz.ui.home.HomeActivity
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject(activity: BaseActivity)

    fun inject(activity: HomeActivity)
}
