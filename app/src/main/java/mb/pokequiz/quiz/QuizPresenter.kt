package mb.pokequiz.quiz

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.functions.Function
import mb.pokequiz.data.json.Pokemon
import mb.pokequiz.data.repository.poke.PokeRepository
import mb.pokequiz.mvp.BasePresenter
import java.net.SocketTimeoutException
import java.util.*

/**
 * Created by mbpeele on 12/27/16.
 */
class QuizPresenter(private val repository: PokeRepository) : BasePresenter<QuizView>() {

    private var randomPokemonDisposable: Disposable? = null
    private val random : Random = Random()

    override fun detach() {
        super.detach()

        randomPokemonDisposable?.dispose()
    }

    fun getNextPokemon() {
        val int = random.nextInt(721)
        randomPokemonDisposable = repository.getPokemon(int).toObservable()
                .onErrorResumeNext(Function<Throwable, ObservableSource<Pokemon>> { t ->
                    if (t is SocketTimeoutException) {
                        return@Function repository.getPokemon(int).toObservable()
                    }

                    Observable.error(t)
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { get()?.showLoading() }
                .subscribe(object : Consumer<Pokemon> {
                    override fun accept(t: Pokemon) {
                       get()?.onPokemonReceived(t)
                    }
                }, object : Consumer<Throwable> {
                    override fun accept(t: Throwable?) {
                        val view = get()
                        if (view != null) {
                            view.hideLoading()
                            view.showError()
                        }
                    }
                })
    }
}