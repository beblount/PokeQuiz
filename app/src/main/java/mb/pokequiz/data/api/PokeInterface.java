package mb.pokequiz.data.api;

import mb.pokequiz.data.model.Pokedex;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by mbpeele on 2/1/16.
 */
public interface PokeInterface {

    @GET("pokedex/1/")
    Observable<Pokedex> getPokedex();
}
