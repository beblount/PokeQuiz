package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.TypeEntity
import mb.pokequiz.data.json.Type

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