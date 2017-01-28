package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.AbilityEntity
import mb.pokequiz.data.json.Ability

/**
 * Created by mbpeele on 1/1/17.
 */
object AbilityMapper {

    fun toModel(entity: AbilityEntity): Ability {
        return Ability(entity.is_hidden!!,
                entity.slot!!,
                NamedResourceMapper.toModel(entity.ability!!))
    }

    fun toEntity(model: Ability): AbilityEntity {
        val entity = AbilityEntity()
        entity.is_hidden = model.is_hidden
        entity.slot = model.slot
        entity.ability = NamedResourceMapper.toEntity(model.ability)

        return entity
    }
}