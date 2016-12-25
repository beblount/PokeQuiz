package mb.pokequiz.data.repository

import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mbpeele on 12/11/16.
 */

interface PokeApi {

    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id: Int) : Observable<Pokedex>

}
