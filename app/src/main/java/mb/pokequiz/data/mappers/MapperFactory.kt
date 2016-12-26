package mb.pokequiz.data.mappers

import kotlin.reflect.KClass

/**
 * Created by mbpeele on 12/26/16.
 */
interface MapperFactory {

    fun <Model, Entity> create(kClass: KClass<*>) : Mapper<Model, Entity>
}