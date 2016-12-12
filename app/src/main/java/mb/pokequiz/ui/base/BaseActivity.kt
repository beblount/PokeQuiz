package mb.pokequiz.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.realm.Realm
import mb.pokequiz.PokeApplication
import mb.pokequiz.dagger.activity.ActivityComponent
import mb.pokequiz.dagger.activity.ActivityModule
import mb.pokequiz.dagger.activity.DaggerActivityComponent

open class BaseActivity : AppCompatActivity() {

    lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        PokeApplication.component.inject(this)

        activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this, Realm.getDefaultInstance()))
                .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        activityComponent.realm().close()
    }
}
