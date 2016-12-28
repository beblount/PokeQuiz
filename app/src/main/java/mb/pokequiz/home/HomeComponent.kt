package mb.pokequiz.home

import dagger.Component
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.application.AppComponent

/**
 * Created by mbpeele on 12/27/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HomeModule::class))
interface HomeComponent {

    fun presenter() : HomePresenter

}