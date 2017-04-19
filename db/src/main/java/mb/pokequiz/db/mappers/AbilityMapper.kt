package mb.pokequiz.db.mappers

import mb.pokequiz.api.model.Ability
import mb.pokequiz.db.entity.AbilityEntity

/**
 * Created by mbpeele on 1/1/17.
 */
object AbilityMapper : Mapper<Ability, AbilityEntity> {

    override fun toModel(entity: AbilityEntity): Ability {
        return Ability(entity.is_hidden!!,
                entity.slot!!,
                NamedResourceMapper.toModel(entity.ability!!))
    }

    override fun toEntity(model: Ability): AbilityEntity {
        val entity = AbilityEntity()
        entity.is_hidden = model.is_hidden
        entity.slot = model.slot
        entity.ability = NamedResourceMapper.toEntity(model.ability)

        return entity
    }
}