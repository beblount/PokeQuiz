package mb.pokequiz.data.repository.poke

import hu.akarnokd.rxjava.interop.RxJavaInterop
import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject
import mb.pokequiz.data.entity.PokedexEntity
import mb.pokequiz.data.entity.PokedexEntityFields
import mb.pokequiz.data.entity.PokemonEntity
import mb.pokequiz.data.entity.PokemonEntityFields
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.model.Pokedex
import mb.pokequiz.data.model.Pokemon

/**
 * Created by mbpeele on 12/24/16.
 */
class RealmRepository(val factory: MapperFactory) : PokeDatabase {

    val tag = RealmRepository::class.simpleName

    override fun save(pokemon: Pokemon) {
        val mapper = factory.create<Pokemon, PokemonEntity>(Pokemon::class)
        val entity = mapper.toEntity(pokemon, factory)
        saveInternal(entity)
    }

    override fun save(pokedex: Pokedex) {
        val mapper = factory.create<Pokedex, PokedexEntity>(Pokedex::class)
        val entity = mapper.toEntity(pokedex, factory)
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
                    val mapper = factory.create<Pokemon, PokemonEntity>(Pokemon::class)
                    mapper.toModel(t, factory)
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
                    val mapper = factory.create<Pokedex, PokedexEntity>(Pokedex::class)
                    mapper.toModel(t, factory)
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