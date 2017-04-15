package mb.pokequiz.application

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import mb.pokequiz.api.source.RemoteApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Module
@Singleton
class WebModule {

    @Provides
    fun pokeApi(retrofit: Retrofit) : RemoteApi {
        return retrofit.create(RemoteApi::class.java)
    }

    @Provides
    fun retrofit(client: OkHttpClient, gson: Gson) : Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .build()
    }

    @Provides
    fun okHttp() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()
    }

    @Provides
    fun gson() : Gson {
        return GsonBuilder()
                .create()
    }
}
