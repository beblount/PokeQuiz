package mb.pokequiz.db.mappers

import mb.pokequiz.api.model.Stat
import mb.pokequiz.db.entity.StatEntity

/**
 * Created by mbpeele on 12/26/16.
 */
object StatMapper : Mapper<Stat, StatEntity> {

    override fun toModel(entity: StatEntity): Stat {
        return Stat(entity.base_stat!!,
                entity.effort!!,
                NamedResourceMapper.toModel(entity.stat!!))
    }

    override fun toEntity(model: Stat): StatEntity {
        val entity = StatEntity()
        entity.effort = model.effort
        entity.base_stat = model.base_stat
        entity.stat = NamedResourceMapper.toEntity(model.stat)
        return entity
    }
}