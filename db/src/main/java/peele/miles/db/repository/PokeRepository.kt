package peele.miles.db.repository

import io.reactivex.Observable
import io.reactivex.Single
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.web.RemoteApi

/**
 * Created by mbpeele on 4/8/17.
 */
class PokeRepository(private val remoteApi: RemoteApi, private val localApi: LocalApi) {

    fun getPokemonById(id: Int) : Single<Pokemon> {
        val web = remoteApi.getPokemon(id)
                .doOnSuccess {
                    localApi.cachePokemon(it)
                }
                .toObservable()

        val local = localApi.getPokemon(id)

        return Observable.merge(web, local).firstOrError()
    }

}