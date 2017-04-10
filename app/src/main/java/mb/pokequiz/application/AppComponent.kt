package mb.pokequiz.application

import android.content.SharedPreferences
import dagger.Component
import peele.miles.db.repository.PokeRepository
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun preferences() : SharedPreferences

    fun repository() : PokeRepository

}
