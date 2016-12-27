package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.StatEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.StatMapper
import mb.pokequiz.data.model.Stat

/**
 * Created by mbpeele on 12/27/16.
 */
class StatMapperTest : MapperTest<Stat, StatEntity>() {
    override fun createMapper(): Mapper<Stat, StatEntity> {
        return StatMapper()
    }

    override fun createModel(): Stat {
        return Stat(randomInt(), randomInt(), createNamedResource())
    }

    override fun createEntity(): StatEntity {
        val entity = StatEntity()
        entity.base_stat = randomInt()
        entity.effort = randomInt()
        entity.stat = createNamedResourceEntity()
        return entity
    }
}