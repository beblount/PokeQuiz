package mb.pokequiz.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import mb.pokequiz.domain.model.Pokemon
import mb.pokequiz.domain.web.RemoteApi

/**
 * Created by mbpeele on 4/8/17.
 */
class PokeRepository(private val remoteApi: RemoteApi, private val localApi: LocalApi) {

    fun getPokemonById(id: Int) : Single<Pokemon> {
        val web = remoteApi.getPokemon(id)
                .doOnNext {
                    localApi.cachePokemon(it)
                }

        return Observable.concat(web, localApi.getPokemon(id))
                .firstOrError()
    }

}