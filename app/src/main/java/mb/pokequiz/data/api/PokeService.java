package mb.pokequiz.data.api;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import mb.pokequiz.R;
import mb.pokequiz.data.model.Pokedex;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;
import rx.Observable;

/**
 * Created by mbpeele on 2/1/16.
 */
public class PokeService {

    private PokeInterface api;

    public PokeService(Application application) {
        String baseUrl = application.getResources().getString(R.string.base_pokeapi_url);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        api = retrofit.create(PokeInterface.class);
    }

    public Observable<Pokedex> getPokedex() {
        return api.getPokedex();
    }
}
