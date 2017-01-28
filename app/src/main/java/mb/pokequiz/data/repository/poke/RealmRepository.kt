package mb.pokequiz.data.repository.poke

import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import mb.pokequiz.data.entity.PokedexEntity
import mb.pokequiz.data.entity.PokedexEntityFields
import mb.pokequiz.data.entity.PokemonEntity
import mb.pokequiz.data.entity.PokemonEntityFields
import mb.pokequiz.data.json.Pokedex
import mb.pokequiz.data.json.Pokemon
import mb.pokequiz.data.mappers.PokeDexMapper
import mb.pokequiz.data.mappers.PokemonMapper

/**
 * Created by mbpeele on 12/24/16.
 */
class RealmRepository : PokeDatabase {

    val tag = RealmRepository::class.simpleName

    override fun save(pokemon: Pokemon) {
        val entity = PokemonMapper.toEntity(pokemon)
        saveInternal(entity)
    }

    override fun save(pokedex: Pokedex) {
        val entity = PokeDexMapper.toEntity(pokedex)
        saveInternal(entity)
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
                  PokemonMapper.toModel(t)
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
                    PokeDexMapper.toModel(t)
                }
                .doOnCompleted { realm.close() }

        return toV2Observable(observable)
    }

    private fun saveInternal(realmObject: RealmObject) {
        Realm.getDefaultInstance().use {
            it.executeTransactionAsync({
                it.copyToRealmOrUpdate(realmObject)
            })
        }
    }

    private fun <T> toV2Observable(observable: rx.Observable<T>) : Observable<T> {
        return RxJavaInterop.toV2Observable(observable)
    }
}