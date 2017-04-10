package mb.pokequiz.data.repository

import io.reactivex.Observable
import io.reactivex.disposables.Disposables
import io.realm.Realm
import io.realm.RealmChangeListener
import io.realm.RealmObject
import mb.pokequiz.data.entity.PokemonEntity
import mb.pokequiz.data.entity.PokemonEntityFields
import mb.pokequiz.data.mappers.PokemonMapper
import mb.pokequiz.domain.model.Pokemon




/**
 * Created by mbpeele on 4/8/17.
 */
class RealmRepository : LocalApi {

    override fun getPokemon(id : Int) : Observable<Pokemon> {
        return getRealm()
                .flatMap {
                    val entity = it.where(PokemonEntity::class.java)
                            .equalTo(PokemonEntityFields.ID, id)
                            .findFirstAsync()
                    createRealmObservable(entity)
                }
                .map {
                    PokemonMapper.toModel(it)
                }
    }

    override fun cachePokemon(pokemon: Pokemon) {
        getRealm()
                .doOnNext {
                    it.executeTransactionAsync {
                        val entity = PokemonMapper.toEntity(pokemon)
                        it.insertOrUpdate(entity)
                    }
                }
    }

    private fun <T : RealmObject> createRealmObservable(entity: T) : Observable<T> {
        val observable : Observable<T> = Observable.create {
            it.onNext(entity)

            val changeListener = RealmChangeListener<T> { element ->
                if (element.isLoaded && !element.isValid)
                    it.onComplete()
                else
                  it.onNext(element)
            }

            RealmObject.addChangeListener(entity, changeListener)

            it.setCancellable({ RealmObject.removeChangeListener(entity, changeListener) })
        }

        return observable.filter { it.isLoaded }
    }

    private fun getRealm() : Observable<Realm> {
        return Observable.create {
            val realm = Realm.getDefaultInstance()

            it.setDisposable(Disposables.fromRunnable {
                realm.close()
            })

            it.onNext(realm)
            it.onComplete()
        }
    }
}