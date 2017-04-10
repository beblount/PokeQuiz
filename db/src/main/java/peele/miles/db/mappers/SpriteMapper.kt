package peele.miles.db.mappers

import mb.pokequiz.api.model.Sprite
import peele.miles.db.entity.SpriteEntity

/**
 * Created by mbpeele on 12/26/16.
 */
object SpriteMapper {

    fun toModel(entity: SpriteEntity): Sprite {
        return Sprite(entity.back_female,
                entity.back_shiny_female,
                entity.back_default,
                entity.front_female,
                entity.front_shiny_female,
                entity.back_shiny,
                entity.front_default,
                entity.front_shiny)
    }

    fun toEntity(model: Sprite): SpriteEntity {
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