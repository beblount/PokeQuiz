package mb.pokequiz.data.repository

import io.reactivex.Observable
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mbpeele on 12/11/16.
 */

interface PokeApi {

    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id: Int) : Observable<Pokedex>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int) : Observable<Pokemon>

}
