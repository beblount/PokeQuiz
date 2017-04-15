package mb.pokequiz.application

import android.content.Context
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mb.pokequiz.api.source.LocalApi
import mb.pokequiz.api.source.PokeApi
import mb.pokequiz.api.source.RemoteApi
import mb.pokequiz.db.repository.PokeRepository
import mb.pokequiz.db.repository.realm.RealmRepository
import mb.pokequiz.presentation.mvp.SchedulerProvider
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
    fun pokeRepository(remoteApi: RemoteApi, localApi: LocalApi) : PokeApi {
        return PokeRepository(remoteApi, localApi)
    }

    @Provides
    fun localApi() : LocalApi {
        return RealmRepository()
    }

    @Provides
    fun schedulerProvider() : SchedulerProvider {
        return object : SchedulerProvider {
            override fun ui(): Scheduler {
                return AndroidSchedulers.mainThread()
            }

            override fun computation(): Scheduler {
                return Schedulers.computation()
            }

            override fun trampoline(): Scheduler {
                return Schedulers.trampoline()
            }

            override fun newThread(): Scheduler {
                return Schedulers.newThread()
            }

            override fun io(): Scheduler {
                return Schedulers.io()
            }
        }
    }
}