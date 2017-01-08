package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.SpriteEntity
import mb.pokequiz.data.json.Sprite

/**
 * Created by mbpeele on 12/26/16.
 */
open class SpriteMapper : Mapper<Sprite, SpriteEntity> {
    override fun toModel(entity: SpriteEntity, factory: MapperFactory): Sprite {
        return Sprite(entity.back_female,
                entity.back_shiny_female,
                entity.back_default,
                entity.front_female,
                entity.front_shiny_female,
                entity.back_shiny,
                entity.front_default,
                entity.front_shiny)
    }

    override fun toEntity(model: Sprite, factory: MapperFactory): SpriteEntity {
        val entity = SpriteEntity()
        entity.back_default = model.back_default
        entity.back_female = model.back_female
        entity.back_shiny = model.back_shiny
        entity.back_shiny_female = model.back_shiny_female
        entity.front_default = model.front_default
        entity.front_female = model.front_female
        entity.front_shiny = model.front_shiny
        entity.front_shiny_female = model.front_shiny_female
        return entity
    }
}