package mb.pokequiz.data.repository

import io.reactivex.Maybe
import mb.pokequiz.data.model.Pokedex

/**
 * Created by mbpeele on 12/24/16.
 */
interface PokeRepository {

    fun getPokedex(id: Int) : Maybe<Pokedex>

}