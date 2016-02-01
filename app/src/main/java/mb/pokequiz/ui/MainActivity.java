package mb.pokequiz.ui;

import android.os.Bundle;

import mb.pokequiz.R;
import mb.pokequiz.data.model.Pokedex;
import mb.pokequiz.util.Logg;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pokeService.getPokedex()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Pokedex>() {
                    @Override
                    public void onCompleted() {
                        Logg.log("COMPLETED");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logg.log(e);
                    }

                    @Override
                    public void onNext(Pokedex pokedex) {
                        Pokedex.DexItem dexItem = pokedex.getDexItems().get(0);
                        Logg.log(dexItem.getName(), dexItem.getUri());
                    }
                });
    }
}

