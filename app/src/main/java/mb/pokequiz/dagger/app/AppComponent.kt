package mb.pokequiz.dagger.app

import dagger.Component
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.repository.PokeApi
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun api() : PokeApi

    fun factory() : MapperFactory
}
