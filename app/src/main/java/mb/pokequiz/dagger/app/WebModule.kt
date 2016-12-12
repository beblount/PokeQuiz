package mb.pokequiz.dagger.app

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import mb.pokequiz.web.api.PokeApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
    fun retrofit(client: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder()
                .baseUrl("http://pokeapi.co/api/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
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
    fun gson() : Gson {
        return GsonBuilder()
                .create()
    }
}
