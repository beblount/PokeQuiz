package mb.pokequiz.dagger;

import javax.inject.Singleton;

import dagger.Component;
import mb.pokequiz.ui.BaseActivity;

/**
 * Created by mbpeele on 2/1/16.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {

    void inject(BaseActivity baseActivity);
}
