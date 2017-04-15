package peele.miles.db.mappers

import mb.pokequiz.api.model.NamedResource
import peele.miles.db.entity.NamedResourceEntity

/**
 * Created by mbpeele on 12/26/16.
 */
object NamedResourceMapper : Mapper<NamedResource, NamedResourceEntity> {

    override fun toModel(entity: NamedResourceEntity): NamedResource {
        return NamedResource(entity.name!!, entity.url!!)
    }

    override fun toEntity(model: NamedResource): NamedResourceEntity {
        val entity = NamedResourceEntity()
        entity.name = model.name
        entity.url = model.url
        return entity
    }
}