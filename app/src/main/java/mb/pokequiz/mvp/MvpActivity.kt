package mb.pokequiz.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mb.pokequiz.application.AppComponent
import mb.pokequiz.application.PokeApplication
import mb.pokequiz.mvp.MvpPresenter
import mb.pokequiz.mvp.MvpView

abstract class MvpActivity<View : MvpView, Presenter : MvpPresenter<View>> : AppCompatActivity() {

    lateinit var presenter : Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = inject()
        presenter.attach(this as View)
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
