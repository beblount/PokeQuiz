package mb.pokequiz.presentation.quiz

import io.reactivex.disposables.Disposable
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.presentation.mvp.BasePresenter
import mb.pokequiz.presentation.mvp.SchedulerProvider
import java.util.*

/**
 * Created by mbpeele on 12/27/16.
 */
class QuizPresenter(val pokeApi: PokeApi, schedulerProvider: SchedulerProvider) : BasePresenter<QuizView>(schedulerProvider) {

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
        randomPokemonDisposable =
                pokeApi.getPokemonById(1)
                .observeOn(schedulerProvider.ui())
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