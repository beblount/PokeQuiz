package mb.pokequiz.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mb.pokequiz.PokeApplication
import mb.pokequiz.R
import mb.pokequiz.dagger.activity.ActivityComponent
import mb.pokequiz.dagger.activity.ActivityModule
import mb.pokequiz.dagger.activity.DaggerActivityComponent

class BaseActivity : AppCompatActivity() {

    lateinit var component : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)

        PokeApplication.component.inject(this)

        component = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        component.realm().close()
    }
}
