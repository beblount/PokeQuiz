package mb.pokequiz.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import mb.pokequiz.application.AppComponent
import mb.pokequiz.application.PokeApplication

abstract class MvpActivity<View : MvpView, Presenter : MvpPresenter<View>> : AppCompatActivity() {

    lateinit var presenter : Presenter

    @Suppress("UNCHECKED_CAST")
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
