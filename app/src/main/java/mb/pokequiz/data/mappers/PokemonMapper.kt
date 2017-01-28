package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.PokemonEntity
import mb.pokequiz.data.json.Pokemon
import mb.pokequiz.data.mappers.MapperFuncs.entityList
import mb.pokequiz.data.mappers.MapperFuncs.modelList

/**
 * Created by mbpeele on 12/26/16.
 */
object PokemonMapper {

    fun toModel(entity: PokemonEntity): Pokemon {
        val types = modelList(entity.types, {
            TypeMapper.toModel(it)
        })
        val forms = modelList(entity.forms, {
            NamedResourceMapper.toModel(it)
        })
        val indices = modelList(entity.game_indices, {
            GameIndexMapper.toModel(it)
        })
        val stats = modelList(entity.stats, {
            StatMapper.toModel(it)
        })
        val abilities = modelList(entity.abilities, {
            AbilityMapper.toModel(it)
        })
        val species = NamedResourceMapper.toModel(entity.species!!)
        val sprite = SpriteMapper.toModel(entity.sprite!!)
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

    fun toEntity(model: Pokemon): PokemonEntity {
        val entity = PokemonEntity()

        entity.id = model.id
        entity.base_experience = model.base_experience
        entity.height = model.height
        entity.is_default = model.is_default
        entity.location_area_encounters = model.location_area_encounters
        entity.name = model.name
        entity.order = model.order
        entity.weight = model.weight
        entity.forms = entityList(model.forms, {
            NamedResourceMapper.toEntity(it)
        })
        entity.game_indices = entityList(model.game_indices, {
            GameIndexMapper.toEntity(it)
        })
        entity.stats = entityList(model.stats, {
            StatMapper.toEntity(it)
        })
        entity.species = NamedResourceMapper.toEntity(model.species)
        entity.sprite = SpriteMapper.toEntity(model.sprites)
        entity.abilities = entityList(model.abilities, {
            AbilityMapper.toEntity(it)
        })
        entity.types = entityList(model.types, {
            TypeMapper.toEntity(it)
        })
        return entity
    }
}