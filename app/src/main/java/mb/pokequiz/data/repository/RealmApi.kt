package mb.pokequiz.data.repository

import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon

/**
 * Created by mbpeele on 12/24/16.
 */
interface RealmApi {

    fun save(pokedex: Pokedex)

    fun save(pokemon: Pokemon)

    fun getPokedex(id: Int) : Observable<Pokedex>

    fun getPokemon(id: Int) : Observable<Pokemon>
}