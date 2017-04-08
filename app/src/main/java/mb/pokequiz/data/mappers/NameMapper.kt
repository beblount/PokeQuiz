package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NameEntity
import mb.pokequiz.domain.model.Name

/**
 * Created by mbpeele on 12/26/16.
 */
object NameMapper {

    fun toEntity(model: Name): NameEntity {
        val entity = NameEntity()
        entity.name = model.name
        entity.language = NamedResourceMapper.toEntity(model.language)
        return entity
    }

    fun toModel(entity: NameEntity): Name {
        return Name(entity.name!!,
                NamedResourceMapper.toModel(entity.language!!))
    }
}