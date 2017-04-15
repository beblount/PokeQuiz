package peele.miles.db.mappers

import mb.pokequiz.api.model.Description
import peele.miles.db.entity.DescriptionEntity

/**
 * Created by mbpeele on 12/26/16.
 */
object DescriptionMapper : Mapper<Description, DescriptionEntity> {

    override fun toEntity(model: Description): DescriptionEntity {
        val entity = DescriptionEntity()
        entity.language = NamedResourceMapper.toEntity(model.language)
        entity.description = model.description
        return entity
    }

    override fun toModel(entity: DescriptionEntity): Description {
        return Description(entity.description!!,
                NamedResourceMapper.toModel(entity.language!!))
    }
}