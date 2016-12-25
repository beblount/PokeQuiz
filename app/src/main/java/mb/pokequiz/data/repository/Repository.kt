package mb.pokequiz.data.repository

import io.reactivex.Maybe
import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex

/**
 * Created by mbpeele on 12/24/16.
 */
class Repository(val localRepository: LocalRepository, val remoteRepository: PokeApi) : PokeRepository {

    override fun getPokedex(id: Int): Maybe<Pokedex> {
        val remote = remoteRepository.getPokedex(id)
                .doOnNext { t -> localRepository.save(t) }

        val local = localRepository.getPokedex(id)

        return Observable.mergeDelayError(remote, local).firstElement()
    }

}