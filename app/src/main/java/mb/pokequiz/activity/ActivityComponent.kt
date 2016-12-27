package mb.pokequiz.activity

import dagger.Component
import mb.pokequiz.activity.ActivityScope
import mb.pokequiz.application.AppComponent
import mb.pokequiz.data.repository.PokeRepository

/**
 * Created by mbpeele on 12/25/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

}