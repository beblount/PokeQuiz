package mb.pokequiz.home

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import mb.pokequiz.base.mvp.BaseMvpPresenter
import mb.pokequiz.data.model.Profile
import mb.pokequiz.data.repository.poke.PokeRepository

/**
 * Created by mbpeele on 12/27/16.
 */
class HomePresenter(private val repository: PokeRepository) : BaseMvpPresenter<HomeView>() {

    private var randomPokemonDisposable: Disposable? = null

    override fun detach() {
        super.detach()

        randomPokemonDisposable?.dispose()
    }

    fun getPokemon(int: Int) {
        repository.getPokemon(int).toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { if (attached()) get().showLoading() }
                .doOnComplete { if (attached()) get().hideLoading() }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (attached()) {
                        get().onPokemonReceive(it)
                    }}, {
                    if (attached()) {
                        get().hideLoading()
                        get().showError()
                    }
                })
    }

    fun getProfile() : Profile {
        return repository.getProfile()
    }
}