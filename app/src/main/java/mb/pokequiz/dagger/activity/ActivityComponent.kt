package mb.pokequiz.dagger.activity

import dagger.Component
import io.realm.Realm
import mb.pokequiz.dagger.ActivityScope

/**
 * Created by mbpeele on 12/11/16.
 */
@ActivityScope
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun realm() : Realm
}
