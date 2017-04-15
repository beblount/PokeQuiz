package peele.miles.db.mappers

import mb.pokequiz.api.model.Name
import peele.miles.db.entity.NameEntity

/**
 * Created by mbpeele on 12/26/16.
 */
object NameMapper : Mapper<Name, NameEntity> {

    override fun toEntity(model: Name): NameEntity {
        val entity = NameEntity()
        entity.name = model.name
        entity.language = NamedResourceMapper.toEntity(model.language)
        return entity
    }

    override fun toModel(entity: NameEntity): Name {
        return Name(entity.name!!,
                NamedResourceMapper.toModel(entity.language!!))
    }
}