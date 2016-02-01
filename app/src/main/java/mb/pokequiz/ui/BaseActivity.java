package mb.pokequiz.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import mb.pokequiz.PokeApplication;
import mb.pokequiz.data.api.PokeService;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by mbpeele on 2/1/16.
 */
public class BaseActivity extends AppCompatActivity {

    @Inject PokeService pokeService;

    private CompositeSubscription compositeSubscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((PokeApplication) getApplication()).getApplicationComponent().inject(this);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
        ButterKnife.unbind(this);
    }

    public void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }

    public void removeSubscription(Subscription subscription) {
        compositeSubscription.remove(subscription);
    }

    public void unsubscribeFromAllSubscriptions() {
        compositeSubscription.unsubscribe();
    }
}
