package mb.pokequiz.data.repository

import io.reactivex.Maybe
import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.data.repository.Database

/**
 * Created by mbpeele on 12/24/16.
 */
class Repository(val database: Database, val remoteRepository: PokeApi) : PokeRepository {

    override fun getPokemon(id: Int): Maybe<Pokemon> {
        val remote = remoteRepository.getPokemon(id)
                .doOnNext { database.save(it) }

        val local = database.getPokemon(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

    override fun getPokedex(id: Int): Maybe<Pokedex> {
        val remote = remoteRepository.getPokedex(id)
                .doOnNext { t -> database.save(t) }

        val local = database.getPokedex(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

}