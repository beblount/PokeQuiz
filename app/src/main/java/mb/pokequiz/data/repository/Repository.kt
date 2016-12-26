package mb.pokequiz.data.repository

import io.reactivex.Maybe
import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon

/**
 * Created by mbpeele on 12/24/16.
 */
class Repository(val realmApi: RealmApi, val remoteRepository: PokeApi) : PokeRepository {

    override fun getPokemon(id: Int): Maybe<Pokemon> {
        val remote = remoteRepository.getPokemon(id)
                .doOnNext { realmApi.save(it) }

        val local = realmApi.getPokemon(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

    override fun getPokedex(id: Int): Maybe<Pokedex> {
        val remote = remoteRepository.getPokedex(id)
                .doOnNext { t -> realmApi.save(t) }

        val local = realmApi.getPokedex(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

}