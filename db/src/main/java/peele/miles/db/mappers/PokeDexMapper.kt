package peele.miles.db.mappers

import mb.pokequiz.api.model.Pokedex
import peele.miles.db.entity.PokedexEntity
import peele.miles.db.mappers.MapperFuncs.entityList
import peele.miles.db.mappers.MapperFuncs.modelList

/**
 * Created by mbpeele on 12/26/16.
 */
object PokeDexMapper {

     fun toEntity(model: Pokedex): PokedexEntity {
        val entity = PokedexEntity()

        val names = entityList(model.names, {
            NameMapper.toEntity(it)
        })
        val descriptions = entityList(model.descriptions, {
            DescriptionMapper.toEntity(it)
        })
        val pokemonEntries = entityList(model.pokemon_entries, {
            PokemonEntryMapper.toEntity(it)
        })
        val versionGroups = entityList(model.version_groups, {
            NamedResourceMapper.toEntity(it)
        })

        entity.id = model.id
        entity.isMainSeries = model.is_main_series
        entity.name = model.name
        entity.descriptions = descriptions
        entity.names = names
        entity.pokemon_entries = pokemonEntries
        entity.version_groups = versionGroups
        entity.region = if (model.region == null) null else NamedResourceMapper.toEntity(model.region!!)

        return entity
    }

    fun toModel(entity: PokedexEntity): Pokedex {
        val names = modelList(entity.names, {
            NameMapper.toModel(it)
        })
        val descriptions = modelList(entity.descriptions, {
            DescriptionMapper.toModel(it)
        })
        val pokemonEntries = modelList(entity.pokemon_entries, {
            PokemonEntryMapper.toModel(it)
        })
        val versionGroups = modelList(entity.version_groups, {
            NamedResourceMapper.toModel(it)
        })
        val region = if (entity.region == null) null else NamedResourceMapper.toModel(entity.region!!)

        return Pokedex(
                entity.id!!,
                entity.name!!,
                entity.isMainSeries!!,
                region,
                names,
                descriptions,
                pokemonEntries,
                versionGroups)
    }
}