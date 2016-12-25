package mb.pokequiz.data.repository

import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import mb.pokequiz.data.entity.PokedexEntity
import mb.pokequiz.data.entity.PokedexEntityFields
import mb.pokequiz.data.model.Mapper
import mb.pokequiz.data.model.Pokedex

/**
 * Created by mbpeele on 12/24/16.
 */
class RealmRepository : LocalRepository {

    override fun save(pokedex: Pokedex) {
        saveInternal(Mapper.PokeDexMapper.toEntity(pokedex))
    }

    override fun getPokedex(id: Int): Observable<Pokedex> {
        val realm = Realm.getDefaultInstance()
        val observable = realm.where(PokedexEntity::class.java)
                .equalTo(PokedexEntityFields.ID, id)
                .findFirstAsync()
                .asObservable<PokedexEntity>()
                .filter { it.isLoaded }
                .first()
                .doOnCompleted { realm.close() }
                .map { t ->
                    Mapper.PokeDexMapper.toModel(t)
                }

        return toV2Observable(observable)
    }

    private fun saveInternal(realmObject: RealmObject) {
        Realm.getDefaultInstance().use {
            it.executeTransaction {
                it.copyToRealmOrUpdate(realmObject)
            }
        }
    }

    private fun <T> toV2Observable(observable: rx.Observable<T>) : Observable<T> {
        return RxJavaInterop.toV2Observable(observable)
    }
}