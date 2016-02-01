package mb.pokequiz.dagger;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mb.pokequiz.util.Datastore;
import mb.pokequiz.data.api.PokeService;

/**
 * Created by mbpeele on 2/1/16.
 */
@Module
@Singleton
public class ApplicationModule {

    private Application mApplication;

    public ApplicationModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    public Application provideAppContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    public Datastore getDatastore(Application mApplication) {
        return new Datastore(mApplication);
    }

    @Provides
    @Singleton
    public PokeService getQuizService(Application application) {
        return new PokeService(application);
    }
}
