package mb.pokequiz.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.util.Log
import io.reactivex.MaybeObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import mb.pokequiz.R
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.databinding.HomeBinding
import mb.pokequiz.ui.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<HomeBinding>(this, R.layout.home)

        binding.activityHome.setOnClickListener {
            component.repository().getPokedex(1)
                .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : MaybeObserver<Pokedex> {
                        override fun onSuccess(value: Pokedex?) {
                            Log.d("TEST", "On success: " + value)
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
}
