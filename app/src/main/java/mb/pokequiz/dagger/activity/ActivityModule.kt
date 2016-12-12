package mb.pokequiz.dagger.activity

import dagger.Module
import dagger.Provides
import io.realm.Realm
import mb.pokequiz.dagger.ActivityScope
import mb.pokequiz.ui.base.BaseActivity

/**
 * Created by mbpeele on 12/11/16.
 */
@Module
class ActivityModule(val activity: BaseActivity, val realm: Realm) {

    @Provides
    @ActivityScope
    fun realm() : Realm {
        return realm
    }
}
