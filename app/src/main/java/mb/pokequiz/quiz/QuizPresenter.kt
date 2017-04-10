package mb.pokequiz.quiz

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import peele.miles.db.repository.PokeRepository
import mb.pokequiz.mvp.BasePresenter
import java.util.*

/**
 * Created by mbpeele on 12/27/16.
 */
class QuizPresenter(val repository: PokeRepository) : BasePresenter<QuizView>() {

    private var randomPokemonDisposable: Disposable? = null
    private val random : Random = Random()

    override fun detach() {
        super.detach()
        randomPokemonDisposable?.dispose()
    }

    fun getNextPokemon() {
        var int = random.nextInt(721)
        while (int == 0) {
            int = random.nextInt(721)
        }
        randomPokemonDisposable = repository.getPokemonById(int).toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    get()?.showLoading()
                }
                .subscribe({
                    get()?.onPokemonReceived(it)
                }, {
                    get()?.showError()
                })
    }
}