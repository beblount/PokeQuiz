package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import mb.pokequiz.persistence.mapper.MapperFactory

/**
 * Created by mbpeele on 12/11/16.
 */
class ModelRepository(var database: DatabaseRepository, val mapperFactory: MapperFactory) : Repository {

    override fun <Model> create(model: Model, clazz: Class<Model>): Single<Model> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Model> update(model: Model): Observable<Model> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Model> delete(model: Model): Single<Void> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}