package mb.pokequiz.api.source

import io.reactivex.Completable
import io.reactivex.Single
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.source.CacheItem

/**
 * Created by mbpeele on 4/9/17.
 */
interface LocalApi {

    fun getPokemon(id: Int) : Single<CacheItem<Pokemon>>

    fun cachePokemon(pokemon: Pokemon)
}