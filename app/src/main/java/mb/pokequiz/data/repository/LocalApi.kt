package mb.pokequiz.data.repository

import io.reactivex.Observable
import mb.pokequiz.domain.model.Pokemon

/**
 * Created by mbpeele on 4/9/17.
 */
interface LocalApi {

    fun getPokemon(id: Int) : Observable<Pokemon>

    fun cachePokemon(pokemon: Pokemon)
}