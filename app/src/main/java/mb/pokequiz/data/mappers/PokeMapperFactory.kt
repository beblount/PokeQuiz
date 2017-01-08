package mb.pokequiz.data.mappers

import mb.pokequiz.data.json.*
import java.util.*
import kotlin.reflect.KClass



/**
 * Created by mbpeele on 12/26/16.
 */
class PokeMapperFactory : MapperFactory {

    val map: HashMap<KClass<*>, Mapper<*, *>> = HashMap()

    init {
        map.put(Description::class, DescriptionMapper())
        map.put(GameIndex::class, GameIndexMapper())
        map.put(NamedResource::class, NamedResourceMapper())
        map.put(Name::class, NameMapper())
        map.put(Pokedex::class, PokeDexMapper())
        map.put(PokemonEntry::class, PokemonEntryMapper())
        map.put(Pokemon::class, PokemonMapper())
        map.put(Sprite::class, SpriteMapper())
        map.put(Stat::class, StatMapper())
        map.put(Ability::class, AbilityMapper())
        map.put(Type::class, TypeMapper())
    }


    @Suppress("UNCHECKED_CAST")
    override fun <Model, Entity> create(kClass: KClass<*>): Mapper<Model, Entity> {
        if (map.contains(kClass)) {
            return map.get(kClass) as Mapper<Model, Entity>
        }

        throw MapperException(kClass)
    }

    class MapperException : Exception {

        constructor(kClass: KClass<*>) {
            ("No mapper exists for: " + kClass.simpleName)
        }

    }
}