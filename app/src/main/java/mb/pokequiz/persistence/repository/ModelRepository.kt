package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by mbpeele on 12/11/16.
 */
@Singleton
class ModelRepository @Inject constructor (var database: DatabaseRepository) : Repository {

    override fun <Model> create(model: Model): Single<Void> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Model> update(model: Model): Observable<Model> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun <Model> delete(model: Model): Single<Void> {
        throw UnsupportedOperationException("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}