package mb.pokequiz.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mb.pokequiz.PokeApplication
import mb.pokequiz.dagger.activity.ActivityComponent
import mb.pokequiz.dagger.activity.ActivityModule
import mb.pokequiz.dagger.activity.DaggerActivityComponent

open class BaseActivity : AppCompatActivity() {

    lateinit var component : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        component = DaggerActivityComponent.builder()
                .appComponent(PokeApplication.component)
                .activityModule(ActivityModule())
                .build()
    }

}
