package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NameEntity
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.model.Name
import mb.pokequiz.data.model.NamedResource

/**
 * Created by mbpeele on 12/26/16.
 */
open class NameMapper : Mapper<Name, NameEntity> {
    override fun toEntity(model: Name, factory: MapperFactory): NameEntity {
        val entity = NameEntity()
        entity.name = model.name
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        entity.language = mapper.toEntity(model.language, factory)
        return entity
    }

    override fun toModel(entity: NameEntity, factory: MapperFactory): Name {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        return Name(entity.name!!,
                mapper.toModel(entity.language!!, factory))
    }
}