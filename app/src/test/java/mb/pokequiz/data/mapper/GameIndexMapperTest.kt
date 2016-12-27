package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.GameIndexEntity
import mb.pokequiz.data.mappers.GameIndexMapper
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.model.GameIndex

/**
 * Created by mbpeele on 12/26/16.
 */
class GameIndexMapperTest : MapperTest<GameIndex, GameIndexEntity>() {

    override fun createMapper(): Mapper<GameIndex, GameIndexEntity> {
        return GameIndexMapper()
    }

    override fun createModel(): GameIndex {
        return GameIndex(randomInt(), createNamedResource())
    }

    override fun createEntity(): GameIndexEntity {
        val entity = GameIndexEntity()
        entity.game_index = randomInt()
        entity.version = createNamedResourceEntity()
        return entity
    }
}