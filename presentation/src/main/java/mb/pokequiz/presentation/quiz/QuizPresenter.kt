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

    private var randomPokemonDisposable: Disposable ? = null
    private val random : Random = Random()
    private var lastInt : Int ?= null

    override fun detach() {
        super.detach()
        randomPokemonDisposable?.dispose()
    }

    fun getNextPokemon() {
        lastInt = random.nextInt(721)
        while (lastInt == 0) {
            lastInt = random.nextInt(721)
        }

        load()
    }

    fun retry() {
        load()
    }

    private fun load() {
        randomPokemonDisposable = pokeApi.getPokemonById(lastInt!!)
                .observeOn(schedulerProvider.ui())
                .doOnSubscribe {
                    get()?.showLoading()
                }
                .subscribe({
                    get()?.onPokemonReceived(it)
                }, {
                    it.printStackTrace()
                    get()?.showError()
                })
    }
}