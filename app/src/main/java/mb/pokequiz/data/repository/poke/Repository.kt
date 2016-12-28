package mb.pokequiz.data.repository.poke

import io.reactivex.Maybe
import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.data.model.Profile

/**
 * Created by mbpeele on 12/24/16.
 */
class Repository(val pokeDatabase: PokeDatabase, val remoteRepository: PokeApi) : PokeRepository {

    override fun getPokemon(id: Int): Maybe<Pokemon> {
        val remote = remoteRepository.getPokemon(id)
                .doOnNext { pokeDatabase.save(it) }

        val local = pokeDatabase.getPokemon(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

    override fun getPokedex(id: Int): Maybe<Pokedex> {
        val remote = remoteRepository.getPokedex(id)
                .doOnNext { t -> pokeDatabase.save(t) }

        val local = pokeDatabase.getPokedex(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

    override fun getProfile(): Profile {
        return pokeDatabase.getProfile()
    }


}