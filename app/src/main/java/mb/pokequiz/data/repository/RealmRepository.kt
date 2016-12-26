package mb.pokequiz.data.repository

import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import mb.pokequiz.data.entity.PokedexEntity
import mb.pokequiz.data.entity.PokedexEntityFields
import mb.pokequiz.data.entity.PokemonEntity
import mb.pokequiz.data.entity.PokemonEntityFields
import mb.pokequiz.data.model.Mapper
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon

/**
 * Created by mbpeele on 12/24/16.
 */
class RealmRepository : RealmApi {

    val tag = RealmRepository::class.simpleName

    override fun save(pokemon: Pokemon) {
        saveInternal(Mapper.PokemonMapper.toEntity(pokemon))
    }

    override fun save(pokedex: Pokedex) {
        saveInternal(Mapper.PokeDexMapper.toEntity(pokedex))
    }

    override fun getPokemon(id: Int): Observable<Pokemon> {
        val realm = Realm.getDefaultInstance()
        val observable = realm.where(PokemonEntity::class.java)
                .equalTo(PokemonEntityFields.ID, id)
                .findFirstAsync()
                .asObservable<PokemonEntity>()
                .filter { it.isLoaded }
                .first()
                .filter { it.isValid }
                .map { t ->
                    Mapper.PokemonMapper.toModel(t)
                }
                .doOnCompleted { realm.close() }

        return toV2Observable(observable)
    }

    override fun getPokedex(id: Int): Observable<Pokedex> {
        val realm = Realm.getDefaultInstance()
        val observable = realm.where(PokedexEntity::class.java)
                .equalTo(PokedexEntityFields.ID, id)
                .findFirstAsync()
                .asObservable<PokedexEntity>()
                .filter { it.isLoaded }
                .first()
                .filter { it.isValid }
                .map { t ->
                    Mapper.PokeDexMapper.toModel(t)
                }
                .doOnCompleted { realm.close() }

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