package mb.pokequiz.ui.home

import android.os.Bundle
import android.util.Log
import io.reactivex.ObservableSource
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import mb.pokequiz.PokeApplication
import mb.pokequiz.R
import mb.pokequiz.persistence.entity.PokedexEntity
import mb.pokequiz.persistence.mapper.PokeMapperFactory
import mb.pokequiz.persistence.repository.ModelDatabase
import mb.pokequiz.persistence.repository.RealmDatabase
import mb.pokequiz.ui.base.BaseActivity
import mb.pokequiz.web.api.PokeApi
import mb.pokequiz.web.model.Pokedex
import javax.inject.Inject

class HomeActivity : BaseActivity() {

    @Inject
    lateinit var api : PokeApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        PokeApplication.component.inject(this)

        val realm = activityComponent.realm()
        val database = RealmDatabase(realm)
        val modelDatabase = ModelDatabase(PokeMapperFactory(), database)

        api.getPokedex("1")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap(object : Function<Pokedex, ObservableSource<Pokedex>> {
                    override fun apply(t: Pokedex?): ObservableSource<Pokedex> {
                        return modelDatabase.create(t!!, Pokedex::class).toObservable()
                    }
                })
                .subscribe(object : Observer<Pokedex> {
                    override fun onSubscribe(d: Disposable?) {
                    }

                    override fun onComplete() {
                        Log.d("TEST", "On complete")
                    }

                    override fun onNext(value: Pokedex?) {
                        Log.d("TEST", value.toString())
                        val results = realm.where(PokedexEntity::class.java).findAll()
                        Log.d("TEST", "RESULTS: " + results.size)
                    }

                    override fun onError(e: Throwable?) {
                        Log.e("TEST", "wtf", e)
                    }
                })
    }
}
