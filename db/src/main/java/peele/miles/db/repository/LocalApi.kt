package peele.miles.db.repository

import io.reactivex.Observable
import mb.pokequiz.api.model.Pokemon

/**
 * Created by mbpeele on 4/9/17.
 */
interface LocalApi {

    fun getPokemon(id: Int) : Observable<Pokemon>

    fun cachePokemon(pokemon: Pokemon)
}