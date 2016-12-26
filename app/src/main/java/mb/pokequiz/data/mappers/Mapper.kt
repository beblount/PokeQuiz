package mb.pokequiz.data.mappers

import io.realm.RealmList
import io.realm.RealmModel
import java.util.*

/**
 * Created by mbpeele on 12/25/16.
 */
interface Mapper<Model, Entity> {

    fun toModel(entity: Entity, factory: MapperFactory): Model

    fun toEntity(model: Model, factory: MapperFactory): Entity

    fun <E, T> modelList(list: List<T>?, mapper: Mapper<E, T>, factory: MapperFactory): List<E> {
        if (list == null) {
            return ArrayList()
        } else {
            return list.map { mapper.toModel(it, factory) }
        }
    }

    fun <E, T : RealmModel?> entityList(list: List<E>?, mapper: Mapper<E, T>, factory: MapperFactory): RealmList<T> {
        if (list == null) {
            return RealmList<T>()
        } else {
            val realmList = RealmList<T>()
            val entities = list.map { mapper.toEntity(it, factory) }
            realmList.addAll(entities)
            return realmList
        }
    }
}