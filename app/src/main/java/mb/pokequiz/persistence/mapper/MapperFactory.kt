package mb.pokequiz.persistence.mapper

import kotlin.reflect.KClass

/**
 * Created by mbpeele on 11/6/16.

 * Implementations of this class must return a [Mapper] that corresponds to the [Class]
 * definition passed into [.create].

 * An even better implementation would be to persist those [Mapper]s to avoid
 * needless object creation.
 */
interface MapperFactory {

    fun <Model> create(domainModelClass: KClass<*>): Mapper<Model>
}