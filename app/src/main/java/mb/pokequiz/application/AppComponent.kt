package mb.pokequiz.application

import dagger.Component
import mb.pokequiz.data.repository.poke.PokeRepository
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun repository() : PokeRepository
}
