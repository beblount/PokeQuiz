package mb.pokequiz.data.repository.poke

import io.reactivex.Maybe
import mb.pokequiz.data.json.Pokedex
import mb.pokequiz.data.json.Pokemon

/**
 * Created by mbpeele on 12/24/16.
 */
interface PokeRepository {

    fun getPokedex(id: Int) : Maybe<Pokedex>

    fun getPokemon(id: Int) : Maybe<Pokemon>

}