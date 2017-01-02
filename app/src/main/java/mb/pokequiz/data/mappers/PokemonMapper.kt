package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.*
import mb.pokequiz.data.model.*

/**
 * Created by mbpeele on 12/26/16.
 */
open class PokemonMapper : Mapper<Pokemon, PokemonEntity> {
    override fun toModel(entity: PokemonEntity, factory: MapperFactory): Pokemon {
        val namedResourceMapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        val gameIndexMapper = factory.create<GameIndex, GameIndexEntity>(GameIndex::class)
        val statMapper = factory.create<Stat, StatEntity>(Stat::class)
        val spriteMapper = factory.create<Sprite, SpriteEntity>(Sprite::class)
        val abilityMapper = factory.create<Ability, AbilityEntity>(Ability::class)
        val typeMapper = factory.create<Type, TypeEntity>(Type::class)

        val types = modelList(entity.types, typeMapper, factory)
        val forms = modelList(entity.forms, namedResourceMapper, factory)
        val indices = modelList(entity.game_indices, gameIndexMapper, factory)
        val stats = modelList(entity.stats, statMapper, factory)
        val abilities = modelList(entity.abilities, abilityMapper, factory)
        val species = namedResourceMapper.toModel(entity.species!!, factory)
        val sprite = spriteMapper.toModel(entity.sprite!!, factory)
        return Pokemon(entity.id!!,
                entity.name!!,
                entity.base_experience!!,
                entity.height!!,
                entity.is_default!!,
                entity.order!!,
                entity.weight!!,
                entity.location_area_encounters!!,
                sprite,
                species,
                abilities,
                forms,
                indices,
                stats,
                types)
    }

    override fun toEntity(model: Pokemon, factory: MapperFactory): PokemonEntity {
        val entity = PokemonEntity()

        val namedResourceMapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        val gameIndexMapper = factory.create<GameIndex, GameIndexEntity>(GameIndex::class)
        val statMapper = factory.create<Stat, StatEntity>(Stat::class)
        val spriteMapper = factory.create<Sprite, SpriteEntity>(Sprite::class)
        val abilityMapper = factory.create<Ability, AbilityEntity>(Ability::class)
        val typeMapper = factory.create<Type, TypeEntity>(Type::class)

        entity.id = model.id
        entity.base_experience = model.base_experience
        entity.height = model.height
        entity.is_default = model.is_default
        entity.location_area_encounters = model.location_area_encounters
        entity.name = model.name
        entity.order = model.order
        entity.weight = model.weight
        entity.forms = entityList(model.forms, namedResourceMapper, factory)
        entity.game_indices = entityList(model.game_indices, gameIndexMapper, factory)
        entity.stats = entityList(model.stats, statMapper, factory)
        entity.species = namedResourceMapper.toEntity(model.species, factory)
        entity.sprite = spriteMapper.toEntity(model.sprites, factory)
        entity.abilities = entityList(model.abilities, abilityMapper, factory)
        entity.types = entityList(model.types, typeMapper, factory)
        return entity
    }
}