package peele.miles.db.repository.realm

import io.reactivex.Completable
import io.reactivex.Single
import io.realm.Realm
import io.realm.RealmObject
import mb.pokequiz.api.model.Pokemon
import peele.miles.db.Database
import peele.miles.db.entity.PokemonEntity
import peele.miles.db.entity.PokemonEntityFields
import peele.miles.db.mappers.Mapper
import peele.miles.db.mappers.PokemonMapper

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
                .doOnDispose {
                    realm.close()
                }
    }

    override fun cachePokemon(pokemon: Pokemon) : Completable {
        val realm = Realm.getInstance(Database.realmConfiguration)
        return RealmAsync.completableTransaction(realm, {
                    val entity = PokemonMapper.toEntity(pokemon)
                    it.insertOrUpdate(entity)
                }).doOnDispose {
                    realm.close()
                }
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