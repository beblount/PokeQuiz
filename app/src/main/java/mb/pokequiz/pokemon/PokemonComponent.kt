package mb.pokequiz.pokemon

import dagger.Component
import mb.pokequiz.application.ActivityScope
import mb.pokequiz.application.AppComponent

/**
 * Created by mbpeele on 12/29/16.
 */
@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(PokemonModule::class))
interface PokemonComponent {

    fun presenter() : PokemonPresenter

}