package mb.pokequiz.application

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import peele.miles.db.repository.realm.LocalApi
import peele.miles.db.repository.PokeRepository
import peele.miles.db.repository.realm.RealmRepository
import mb.pokequiz.api.web.RemoteApi
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
@Module(includes = arrayOf(WebModule::class))
class AppModule(var application: PokeApplication) {

    @Singleton
    @Provides
    fun application() : PokeApplication {
        return application;
    }

    @Provides
    @Singleton
    fun preferences() : SharedPreferences {
        return application.getSharedPreferences("pokemon", Context.MODE_PRIVATE)
    }

    @Provides
    fun pokeRepository(remoteApi: RemoteApi, localApi: LocalApi) : PokeRepository {
        return PokeRepository(remoteApi, localApi)
    }

    @Provides
    fun localApi() : LocalApi {
        return RealmRepository()
    }
}