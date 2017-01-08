package mb.pokequiz.data.mapper

import io.realm.RealmList
import mb.pokequiz.data.entity.*
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.PokemonMapper
import mb.pokequiz.data.json.*
import java.util.*

/**
 * Created by mbpeele on 12/27/16.
 */
class PokemonMapperTest : MapperTest<Pokemon, PokemonEntity>() {
    override fun createMapper(): Mapper<Pokemon, PokemonEntity> {
        return PokemonMapper()
    }

    override fun createModel(): Pokemon {
        var size = randomInt()
        val resources = ArrayList<NamedResource>(size)
        for (i in 0 until size) {
            resources.add(createNamedResource())
        }

        size = randomInt()
        val gameIndices = ArrayList<GameIndex>(size)
        for (i in 0 until size) {
            val index = GameIndex(randomInt(), createNamedResource())
            gameIndices.add(index)
        }

        size = randomInt()
        val stats = ArrayList<Stat>(size)
        for (i in 0 until size) {
            val stat = Stat(randomInt(), randomInt(), createNamedResource())
            stats.add(stat)
        }

        val sprite = Sprite(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), randomString())

        return Pokemon(randomInt(), randomString(), randomInt(), randomInt(), randomBool(),
                randomInt(), randomInt(), randomString(), sprite, createNamedResource(),
                resources, gameIndices, stats)
    }

    override fun createEntity(): PokemonEntity {
        val entity = PokemonEntity()
        entity.id = randomInt()
        entity.base_experience = randomInt()
        entity.height = randomInt()
        entity.is_default = randomBool()
        entity.location_area_encounters = randomString()
        entity.name = randomString()
        entity.order = randomInt()
        entity.weight = randomInt()
        entity.species = createNamedResourceEntity()

        val sprite = SpriteEntity()
        sprite.back_default = null
        sprite.back_female = null
        sprite.back_shiny = randomString()
        sprite.back_shiny_female = randomString()
        sprite.front_female = randomString()
        sprite.front_shiny_female = null
        sprite.front_shiny = randomString()
        sprite.front_default = null
        entity.sprite = sprite

        var size = randomInt()
        val resources = RealmList<NamedResourceEntity>()
        for (i in 0 until size) {
            resources.add(createNamedResourceEntity())
        }
        entity.forms = resources

        size = randomInt()
        val gameIndices = RealmList<GameIndexEntity>()
        for (i in 0 until size) {
            val index = GameIndexEntity()
            index.game_index = randomInt()
            index.version = createNamedResourceEntity()
            gameIndices.add(index)
        }
        entity.game_indices = gameIndices

        size = randomInt()
        val stats = RealmList<StatEntity>()
        for (i in 0 until size) {
            val stat = StatEntity()
            stat.base_stat = randomInt()
            stat.effort = randomInt()
            stat.stat = createNamedResourceEntity()
            stats.add(stat)
        }
        entity.stats = stats

        return entity
    }

}