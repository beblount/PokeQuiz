package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.DescriptionEntity
import mb.pokequiz.data.json.Description

/**
 * Created by mbpeele on 12/26/16.
 */
object DescriptionMapper {

    fun toEntity(model: Description): DescriptionEntity {
        val entity = DescriptionEntity()
        entity.language = NamedResourceMapper.toEntity(model.language)
        entity.description = model.description
        return entity
    }

    fun toModel(entity: DescriptionEntity): Description {
        return Description(entity.description!!,
                NamedResourceMapper.toModel(entity.language!!))
    }
}