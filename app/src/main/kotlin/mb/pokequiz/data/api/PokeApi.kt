package mb.pokequiz.data.api

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by mbpeele on 12/11/16.
 */

interface PokeApi {

    @GET("/pokedex/{id}")
    fun getPokedex(@Query("id") idOrName : String) : Observable<Void>

}
