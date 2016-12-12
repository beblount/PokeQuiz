package mb.pokequiz.ui.home

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mb.pokequiz.PokeApplication
import mb.pokequiz.R
import mb.pokequiz.web.api.PokeApi
import mb.pokequiz.web.model.Pokedex
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    @Inject
    lateinit var api : PokeApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        PokeApplication.component.inject(this)

        api.getPokedex("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Pokedex> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {
                        Log.d("TEST", "On complete")
                    }

                    override fun onNext(value: Pokedex?) {
                        Log.d("TEST", value.toString())
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TEST", "wtf", e)
                    }
                })
    }
}
