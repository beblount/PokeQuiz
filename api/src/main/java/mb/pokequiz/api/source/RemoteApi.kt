package mb.pokequiz.api.source

import io.reactivex.Single
import mb.pokequiz.api.model.Pokedex
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.model.Species
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by mbpeele on 12/11/16.
 */

interface RemoteApi {

    @GET("pokedex/{id}")
    fun getPokedex(@Path("id") id: Int) : Single<Pokedex>

    @GET("pokemon/{id}")
    fun getPokemon(@Path("id") id: Int) : Single<Pokemon>

    @GET("pokemon-species/{id}")
    fun getSpecies(@Path("id") id: Int) : Single<Species>

}
