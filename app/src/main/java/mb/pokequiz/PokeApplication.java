package mb.pokequiz;

import android.app.Application;

import mb.pokequiz.dagger.ApplicationComponent;
import mb.pokequiz.dagger.ApplicationModule;
import mb.pokequiz.dagger.DaggerApplicationComponent;

/**
 * Created by mbpeele on 2/1/16.
 */
public class PokeApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return component;
    }
}
