package mb.pokequiz.data.repository

import io.reactivex.Observable
import io.reactivex.Single
import mb.pokequiz.domain.model.Pokemon
import mb.pokequiz.domain.web.PokeApi

/**
 * Created by mbpeele on 4/8/17.
 */
class PokeRepository(private val pokeApi: PokeApi) {

    private val repository: RealmRepository = RealmRepository()

    fun getPokemonById(id: Int) : Single<Pokemon> {
        val web = pokeApi.getPokemon(id)
                .doOnNext {
                    repository.cachePokemon(it)
                }

        return Observable.concat(web, repository.getPokemon(id))
                .firstOrError()
    }

}