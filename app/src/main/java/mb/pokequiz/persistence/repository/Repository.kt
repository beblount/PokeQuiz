package mb.pokequiz.persistence.repository

import io.reactivex.Observable
import io.reactivex.Single
import kotlin.reflect.KClass

/**
 * Created by mbpeele on 12/11/16.
 */
interface Repository {

    fun <Model : Any> create(model: Model, clazz: KClass<*>): Single<Model>

    fun <Model> update(model: Model) : Observable<Model>

    fun <Model> delete(model: Model) : Single<Void>
}