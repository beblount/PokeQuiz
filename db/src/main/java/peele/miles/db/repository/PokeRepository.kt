package peele.miles.db.repository

import io.reactivex.Completable
import io.reactivex.Single
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.web.RemoteApi
import peele.miles.db.repository.realm.CacheItem
import peele.miles.db.repository.realm.LocalApi

/**
 * Created by mbpeele on 4/8/17.
 */
class PokeRepository(private val remoteApi: RemoteApi, private val localApi: LocalApi) {

    fun getPokemonById(id: Int) : Single<Pokemon> {
        return resolve(localApi.getPokemon(id), remoteApi.getPokemon(id), {
            localApi.cachePokemon(it)
        })
    }

    private fun <T> resolve(localQuery: Single<CacheItem<T>>, web: Single<T>,
                            function: (value: T) -> Completable) : Single<T> {
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