package mb.pokequiz.api.source

import io.reactivex.Single
import mb.pokequiz.api.model.Pokemon

/**
 * Created by mbpeele on 4/15/17.
 */
interface PokeApi {

    fun getPokemonById(id: Int) : Single<Pokemon>
}