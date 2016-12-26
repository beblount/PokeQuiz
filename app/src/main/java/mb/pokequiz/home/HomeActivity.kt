package mb.pokequiz.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import mb.pokequiz.R
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.HomeBinding
import mb.pokequiz.ui.base.BaseActivity
import java.util.concurrent.TimeUnit

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<HomeBinding>(this, R.layout.home)

        val repository = component.repository()

        Observable.intervalRange(1, 721, 0, 15, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .flatMap {
                    repository.getPokemon(it.toInt()).toObservable()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<Pokemon> {
                    override fun onNext(value: Pokemon?) {
                        Log.d("TEST", "On success: " + value)
                        binding.pokemon = value
                    }

                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {
                        Log.d("TEST", "On complete")
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TEST", "wtf", e)
                    }
                })
    }
}
