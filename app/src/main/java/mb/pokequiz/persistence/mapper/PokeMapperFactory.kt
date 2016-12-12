package mb.pokequiz.persistence.mapper

import mb.pokequiz.web.model.Pokedex
import kotlin.reflect.KClass

/**
 * Created by mbpeele on 12/11/16.
 */

class PokeMapperFactory : MapperFactory {

    val factory : MapperFactory

    init {
        factory = MapperFactoryBuilder()
                .register(Pokedex::class, PokeDexMapper())
                .build()
    }

    override fun <Model> create(domainModelClass: KClass<*>): Mapper<Model> {
        return factory.create(domainModelClass)
    }

}
