package mb.pokequiz.db.repository.realm

import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.api.source.CacheItem
import mb.pokequiz.api.source.LocalApi
import mb.pokequiz.db.Database
import mb.pokequiz.db.entity.PokemonEntity
import mb.pokequiz.db.entity.PokemonEntityFields
import mb.pokequiz.db.mappers.Mapper
import mb.pokequiz.db.mappers.PokemonMapper

/**
 * Created by mbpeele on 4/8/17.
 */
class RealmRepository : LocalApi {

    override fun getPokemon(id : Int) : Single<CacheItem<Pokemon>> {
        val realm = Realm.getInstance(Database.realmConfiguration)
        val entity = realm.where(PokemonEntity::class.java)
                .equalTo(PokemonEntityFields.ID, id)
                .findFirstAsync()
        return RxRealm.single(entity)
                .map {
                    toCacheItem(it, PokemonMapper)
                }
    }

    override fun cachePokemon(pokemon: Pokemon) {
        val realm = Realm.getInstance(Database.realmConfiguration)
        realm.executeTransaction {
            val entity = PokemonMapper.toEntity(pokemon)
            it.insertOrUpdate(entity)
        }
        realm.close()
    }

    private fun <M, E: RealmObject> toCacheItem(entity: E, mapper: Mapper<M, E>) : CacheItem<M> {
        if (entity.isValid) {
            val model = mapper.toModel(entity)
            return CacheItem(model)
        } else {
            return CacheItem.NOT_FOUND
        }
    }
}