package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single

/**
 * Created by mbpeele on 12/11/16.
 */
interface Repository {

    fun <Model> create(model: Model, clazz: Class<Model>): Single<Model>

    fun <Model> update(model: Model) : Observable<Model>

    fun <Model> delete(model: Model) : Single<Void>
}