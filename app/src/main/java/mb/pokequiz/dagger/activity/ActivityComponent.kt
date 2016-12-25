package mb.pokequiz.dagger.activity

import dagger.Component
import mb.pokequiz.dagger.ActivityScope
import mb.pokequiz.dagger.app.AppComponent
import mb.pokequiz.data.repository.PokeRepository

/**
 * Created by mbpeele on 12/25/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun repository() : PokeRepository

}