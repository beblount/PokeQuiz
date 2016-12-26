package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.DescriptionEntity
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.model.Description
import mb.pokequiz.data.model.NamedResource

/**
 * Created by mbpeele on 12/26/16.
 */
open class DescriptionMapper : Mapper<Description, DescriptionEntity> {
    override fun toEntity(model: Description, factory: MapperFactory): DescriptionEntity {
        val entity = DescriptionEntity()
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        entity.language = mapper.toEntity(model.language, factory)
        entity.description = model.description
        return entity
    }

    override fun toModel(entity: DescriptionEntity, factory: MapperFactory): Description {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        return Description(entity.description!!,
                mapper.toModel(entity.language!!, factory))
    }
}