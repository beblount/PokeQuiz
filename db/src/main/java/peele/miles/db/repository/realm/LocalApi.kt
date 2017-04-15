package peele.miles.db.repository.realm

import io.reactivex.Completable
import io.reactivex.Single
import mb.pokequiz.api.model.Pokemon

/**
 * Created by mbpeele on 4/9/17.
 */
interface LocalApi {

    fun getPokemon(id: Int) : Single<CacheItem<Pokemon>>

    fun cachePokemon(pokemon: Pokemon) : Completable
}