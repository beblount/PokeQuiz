package mb.pokequiz.persistence.mapper

import io.realm.RealmObject
import mb.pokequiz.web.model.Pokedex
import java.util.*
import kotlin.reflect.KClass

/**
 * Created by mbpeele on 12/11/16.
 */
class PokeMapperFactory : MapperFactory {

    val hashmap: HashMap<KClass<out Any>, Mapper<KClass<out Any>, RealmObject>>

    init {
        hashmap = HashMap()
        hashmap.put(Pokedex::class, PokeDexMapper() as Mapper<KClass<out Any>, RealmObject>)
    }

    override fun create(clazz: KClass<out Any>): Mapper<KClass<out Any>, RealmObject> {
        if (!hashmap.contains(clazz)) {
            throw IllegalStateException("Mapper Factory does not contain Mapper for: " + clazz.simpleName)
        }

        return hashmap.get(clazz)!!
    }
}