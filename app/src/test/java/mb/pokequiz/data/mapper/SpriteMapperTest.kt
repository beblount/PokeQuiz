package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.SpriteEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.SpriteMapper
import mb.pokequiz.data.model.Sprite

/**
 * Created by mbpeele on 12/27/16.
 */
class SpriteMapperTest : MapperTest<Sprite, SpriteEntity>() {

    override fun createMapper(): Mapper<Sprite, SpriteEntity> {
        return SpriteMapper()
    }

    override fun createModel(): Sprite {
        return Sprite(randomString(), randomString(), randomString(), randomString(),
                randomString(), randomString(), randomString(), randomString())
    }

    override fun createEntity(): SpriteEntity {
        val entity = SpriteEntity()
        entity.back_default = null
        entity.back_female = null
        entity.back_shiny = randomString()
        entity.back_shiny_female = randomString()
        entity.front_female = randomString()
        entity.front_shiny_female = null
        entity.front_shiny = randomString()
        entity.front_default = null
        return entity
    }
}