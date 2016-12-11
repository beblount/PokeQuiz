package mb.pokequiz.dagger.app

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import mb.pokequiz.web.api.PokeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Module
class WebModule {

    @Provides
    @Singleton
    fun pokeApi(retrofit: Retrofit) : PokeApi {
        return retrofit.create(PokeApi::class.java)
    }

    @Provides
    @Singleton
    fun retrofit(client: OkHttpClient, moshi: Moshi) : Retrofit {
        return Retrofit.Builder()
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    fun okHttp() : OkHttpClient {
        return OkHttpClient.Builder()
                .build()
    }

    @Provides
    @Singleton
    fun moshi() : Moshi {
        return Moshi.Builder()
                .build()
    }
}
