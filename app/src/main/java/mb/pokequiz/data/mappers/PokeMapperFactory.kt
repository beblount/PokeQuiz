package mb.pokequiz.data.mappers

import mb.pokequiz.data.model.*
import java.util.*
import kotlin.reflect.KClass



/**
 * Created by mbpeele on 12/26/16.
 */
class PokeMapperFactory : MapperFactory {

    val map: HashMap<KClass<*>, Mapper<*, *>>

    init {
        map = HashMap()
        map.put(Description::class, DescriptionMapper())
        map.put(GameIndex::class, GameIndexMapper())
        map.put(NamedResource::class, NamedResourceMapper())
        map.put(Name::class, NameMapper())
        map.put(Pokedex::class, PokeDexMapper())
        map.put(PokemonEntry::class, PokemonEntryMapper())
        map.put(Pokemon::class, PokemonMapper())
        map.put(Sprite::class, SpriteMapper())
        map.put(Stat::class, StatMapper())
    }


    override fun <Model, Entity> create(kClass: KClass<*>): Mapper<Model, Entity> {
        if (map.contains(kClass)) {
            return map.get(kClass) as Mapper<Model, Entity>
        }

        throw RuntimeException(kClass.simpleName.plus(" doesn't exist in package"))
    }
}