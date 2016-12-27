package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.*
import mb.pokequiz.data.model.*

/**
 * Created by mbpeele on 12/26/16.
 */
open class PokeDexMapper : Mapper<Pokedex, PokedexEntity> {
    override fun toEntity(model: Pokedex, factory: MapperFactory): PokedexEntity {
        val entity = PokedexEntity()

        val nameMapper = factory.create<Name, NameEntity>(Name::class)
        val descriptionMapper = factory.create<Description, DescriptionEntity>(Description::class)
        val pokemonEntryMapper = factory.create<PokemonEntry, PokemonEntryEntity>(PokemonEntry::class)
        val namedResourceMapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        val names = entityList(model.names, nameMapper, factory)
        val descriptions = entityList(model.descriptions, descriptionMapper, factory)
        val pokemonEntries = entityList(model.pokemon_entries, pokemonEntryMapper, factory)
        val versionGroups = entityList(model.version_groups, namedResourceMapper, factory)

        entity.id = model.id
        entity.isMainSeries = model.is_main_series
        entity.name = model.name
        entity.descriptions = descriptions
        entity.names = names
        entity.pokemon_entries = pokemonEntries
        entity.version_groups = versionGroups
        entity.region = if (model.region == null) null else namedResourceMapper.toEntity(model.region, factory)

        return entity
    }

    override fun toModel(entity: PokedexEntity, factory: MapperFactory): Pokedex {
        val nameMapper = factory.create<Name, NameEntity>(Name::class)
        val descriptionMapper = factory.create<Description, DescriptionEntity>(Description::class)
        val pokemonEntryMapper = factory.create<PokemonEntry, PokemonEntryEntity>(PokemonEntry::class)
        val namedResourceMapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)

        val names = modelList(entity.names, nameMapper, factory)
        val descriptions = modelList(entity.descriptions, descriptionMapper, factory)
        val pokemonEntries = modelList(entity.pokemon_entries, pokemonEntryMapper, factory)
        val versionGroups = modelList(entity.version_groups, namedResourceMapper, factory)
        val region = if (entity.region == null) null else namedResourceMapper.toModel(entity.region!!, factory)

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