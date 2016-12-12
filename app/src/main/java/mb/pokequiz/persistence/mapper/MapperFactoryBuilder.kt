package mb.pokequiz.persistence.mapper

import java.util.*
import kotlin.reflect.KClass

/**
 * Created by mbpeele on 11/9/16.
 */
internal class MapperFactoryBuilder {

    private val hashMap: HashMap<KClass<*>, Mapper<*>>

    init {
        this.hashMap = HashMap()
    }

    fun register(clazz: KClass<*>, mapper: Mapper<*>): MapperFactoryBuilder {
        hashMap.put(clazz, mapper)
        return this
    }

    fun build(): MapperFactory {
        return object : MapperFactory {
            override fun <Model> create(domainModelClass: KClass<*>): Mapper<Model> {
                val mapper = hashMap[domainModelClass] ?: throw RuntimeException("No Mapper registered for: " + domainModelClass.simpleName)

                return mapper as Mapper<Model>
            }
        }
    }
}