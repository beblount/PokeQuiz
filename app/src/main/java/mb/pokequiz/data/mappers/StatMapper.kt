package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.StatEntity
import mb.pokequiz.domain.model.Stat

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