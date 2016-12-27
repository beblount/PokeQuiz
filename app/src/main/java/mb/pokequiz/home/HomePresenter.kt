package mb.pokequiz.home

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import mb.pokequiz.base.mvp.BaseMvpPresenter
import mb.pokequiz.data.repository.PokeRepository
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by mbpeele on 12/27/16.
 */
class HomePresenter(val repository: PokeRepository) : BaseMvpPresenter<HomeView>() {

    var randomPokemonDisposable: Disposable? = null

    override fun detach() {
        super.detach()

        randomPokemonDisposable?.dispose()
    }

    fun getRandomPokemon() {
        val random = Random()

        randomPokemonDisposable = Observable.intervalRange(1, 721, 0, 10, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                .flatMap {
                    repository.getPokemon(random.nextInt(721)).toObservable()
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            if (attached()) {
                                get().onPokemonReceive(it)
                            }
                        }, {
                            throw UnsupportedOperationException("not implemented")
                        }, {
                             throw UnsupportedOperationException("not implemented")
                })
    }
}