package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.entity.TypeEntity
import mb.pokequiz.data.model.NamedResource
import mb.pokequiz.data.model.Type

/**
 * Created by mbpeele on 1/1/17.
 */
class TypeMapper : Mapper<Type, TypeEntity> {

    override fun toModel(entity: TypeEntity, factory: MapperFactory): Type {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        return Type(entity.slot!!, mapper.toModel(entity.type!!, factory))
    }

    override fun toEntity(model: Type, factory: MapperFactory): TypeEntity {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        val entity = TypeEntity()
        entity.slot = model.slot
        entity.type = mapper.toEntity(model.type, factory)
        return entity
    }
}