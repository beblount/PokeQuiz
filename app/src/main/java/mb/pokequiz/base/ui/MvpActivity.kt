package mb.pokequiz.base.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mb.pokequiz.activity.ActivityComponent
import mb.pokequiz.activity.ActivityModule
import mb.pokequiz.activity.DaggerActivityComponent
import mb.pokequiz.application.AppComponent
import mb.pokequiz.application.PokeApplication
import mb.pokequiz.base.mvp.MvpPresenter
import mb.pokequiz.base.mvp.MvpView

abstract class MvpActivity<View : MvpView, Presenter : MvpPresenter<View>> : AppCompatActivity() {

    lateinit var component : ActivityComponent
    lateinit var presenter : Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = inject()
        presenter.attach(this as View)

        component = DaggerActivityComponent.builder()
                .appComponent(appComponent())
                .activityModule(ActivityModule())
                .build()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach()
    }

    abstract fun inject() : Presenter

    fun appComponent() : AppComponent {
        return (application as PokeApplication).appComponent
    }


}
