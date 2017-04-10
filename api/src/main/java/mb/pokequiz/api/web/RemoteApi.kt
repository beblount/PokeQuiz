package mb.pokequiz.api.web

import io.reactivex.Observable
import mb.pokequiz.api.model.Pokedex
import mb.pokequiz.api.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mbpeele on 12/11/16.
 */

interface RemoteApi {

    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id: Int) : Observable<Pokedex>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int) : Observable<Pokemon>

}
