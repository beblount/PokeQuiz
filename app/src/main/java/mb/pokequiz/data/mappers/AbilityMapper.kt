package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.AbilityEntity
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.model.Ability
import mb.pokequiz.data.model.NamedResource

/**
 * Created by mbpeele on 1/1/17.
 */
class AbilityMapper : Mapper<Ability, AbilityEntity> {

    override fun toModel(entity: AbilityEntity, factory: MapperFactory): Ability {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        return Ability(entity.is_hidden!!,
                entity.slot!!,
                mapper.toModel(entity.ability!!, factory))
    }

    override fun toEntity(model: Ability, factory: MapperFactory): AbilityEntity {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        val entity = AbilityEntity()
        entity.is_hidden = model.is_hidden
        entity.slot = model.slot
        entity.ability = mapper.toEntity(model.ability, factory)

        return entity
    }
}