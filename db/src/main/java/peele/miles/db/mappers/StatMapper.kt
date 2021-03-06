package peele.miles.db.mappers

import mb.pokequiz.api.model.Stat
import peele.miles.db.entity.StatEntity

/**
 * Created by mbpeele on 12/26/16.
 */
object StatMapper {

    fun toModel(entity: StatEntity): Stat {
        return Stat(entity.base_stat!!,
                entity.effort!!,
                NamedResourceMapper.toModel(entity.stat!!))
    }

    fun toEntity(model: Stat): StatEntity {
        val entity = StatEntity()
        entity.effort = model.effort
        entity.base_stat = model.base_stat
        entity.stat = NamedResourceMapper.toEntity(model.stat)
        return entity
    }
}