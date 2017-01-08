package mb.pokequiz.data.repository.poke

import io.reactivex.Observable
import mb.pokequiz.data.json.Pokedex
import mb.pokequiz.data.json.Pokemon

/**
 * Created by mbpeele on 12/24/16.
 */
interface PokeDatabase {

    fun save(pokedex: Pokedex)

    fun save(pokemon: Pokemon)

    fun getPokedex(id: Int) : Observable<Pokedex>

    fun getPokemon(id: Int) : Observable<Pokemon>
}