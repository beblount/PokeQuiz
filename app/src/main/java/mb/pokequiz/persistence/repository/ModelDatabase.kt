package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import mb.pokequiz.persistence.mapper.MapperFactory
import kotlin.reflect.KClass

/**
 * Created by mbpeele on 12/11/16.
 */

class ModelDatabase(val factory: MapperFactory, val database : RealmRepository) : Repository {

    override fun <Model : Any> create(model: Model, clazz: KClass<*>): Single<Model> {
        val mapper = factory.create<Model>(clazz)
        val entity = mapper.toEntity(model, factory)
        return database.create(entity)
                .map { t -> mapper.fromEntity(t, factory) }
    }

    override fun <Model> update(model: Model): Observable<Model> {
        throw UnsupportedOperationException("not implemented")
    }

    override fun <Model> delete(model: Model): Single<Void> {
        throw UnsupportedOperationException("not implemented")
    }

}
