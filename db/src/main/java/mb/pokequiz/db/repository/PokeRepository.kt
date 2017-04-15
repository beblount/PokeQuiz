package mb.pokequiz.db.repository

import io.reactivex.Single
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.source.CacheItem
import mb.pokequiz.api.source.LocalApi
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.api.source.RemoteApi

/**
 * Created by mbpeele on 4/8/17.
 */
class PokeRepository(private val remoteApi: RemoteApi, private val localApi: LocalApi) : PokeApi {

    override fun getPokemonById(id: Int) : Single<Pokemon> {
        return resolve(localApi.getPokemon(id), remoteApi.getPokemon(id), {
            localApi.cachePokemon(it)
        })
    }

    private fun <T> resolve(localQuery: Single<CacheItem<T>>, web: Single<T>,
                            function: (value: T) -> Unit) : Single<T> {
        return localQuery
                .flatMap {
                    if (it == CacheItem.NOT_FOUND) {
                        web.doOnSuccess {
                            function.invoke(it)
                        }
                    } else {
                        Single.just(it.item)
                    }
                }
    }

}