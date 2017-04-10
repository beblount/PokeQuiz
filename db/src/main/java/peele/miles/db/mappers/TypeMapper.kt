package peele.miles.db.mappers

import mb.pokequiz.api.model.Type
import peele.miles.db.entity.TypeEntity

/**
 * Created by mbpeele on 1/1/17.
 */
object TypeMapper {

    fun toModel(entity: TypeEntity): Type {
        return Type(entity.slot!!,
                NamedResourceMapper.toModel(entity.type!!))
    }

    fun toEntity(model: Type): TypeEntity {
        val entity = TypeEntity()
        entity.slot = model.slot
        entity.type = NamedResourceMapper.toEntity(model.type)
        return entity
    }
}