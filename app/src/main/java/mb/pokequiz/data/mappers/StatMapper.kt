package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.entity.StatEntity
import mb.pokequiz.data.model.NamedResource
import mb.pokequiz.data.model.Stat

/**
 * Created by mbpeele on 12/26/16.
 */
open class StatMapper : Mapper<Stat, StatEntity> {
    override fun toModel(entity: StatEntity, factory: MapperFactory): Stat {
        val namedResourceMapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        return Stat(entity.base_stat!!,
                entity.effort!!,
                namedResourceMapper.toModel(entity.stat!!, factory))
    }

    override fun toEntity(model: Stat, factory: MapperFactory): StatEntity {
        val entity = StatEntity()
        entity.effort = model.effort
        entity.base_stat = model.base_stat
        val namedResourceMapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        entity.stat = namedResourceMapper.toEntity(model.stat, factory)
        return entity
    }
}