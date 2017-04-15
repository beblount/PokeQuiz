package mb.pokequiz.application

import android.content.SharedPreferences
import dagger.Component
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.presentation.mvp.SchedulerProvider
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun preferences() : SharedPreferences

    fun repository() : PokeApi

    fun schedulerProvider() : SchedulerProvider

}
