package mb.pokequiz.persistence.mapper

import io.realm.RealmObject
import kotlin.reflect.KClass

/**
 * Created by mbpeele on 12/11/16.
 */
interface MapperFactory {

    fun create(clazz: KClass<out Any>): Mapper<KClass<out Any>, RealmObject>
}