package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.json.NamedResource

/**
 * Created by mbpeele on 12/26/16.
 */
open class NamedResourceMapper : Mapper<NamedResource, NamedResourceEntity> {
    override fun toModel(entity: NamedResourceEntity, factory: MapperFactory): NamedResource {
        return NamedResource(entity.name!!, entity.url!!)
    }

    override fun toEntity(model: NamedResource, factory: MapperFactory): NamedResourceEntity {
        val entity = NamedResourceEntity()
        entity.name = model.name
        entity.url = model.url
        return entity
    }
}