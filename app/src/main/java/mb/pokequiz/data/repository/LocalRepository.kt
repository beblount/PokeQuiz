package mb.pokequiz.data.repository

import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex

/**
 * Created by mbpeele on 12/24/16.
 */
interface LocalRepository {

    fun save(pokedex: Pokedex)

    fun getPokedex(id: Int) : Observable<Pokedex>
}