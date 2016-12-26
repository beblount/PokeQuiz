package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.entity.PokemonEntryEntity
import mb.pokequiz.data.model.NamedResource
import mb.pokequiz.data.model.PokemonEntry

/**
 * Created by mbpeele on 12/26/16.
 */
open class PokemonEntryMapper : Mapper<PokemonEntry, PokemonEntryEntity> {
    override fun toEntity(model: PokemonEntry, factory: MapperFactory): PokemonEntryEntity {
        val entity = PokemonEntryEntity()
        entity.entryNumber = model.entry_number
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        entity.pokemonSpeciesEntity = mapper.toEntity(model.pokemon_species, factory)
        return entity
    }

    override fun toModel(entity: PokemonEntryEntity, factory: MapperFactory): PokemonEntry {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        return PokemonEntry(entity.entryNumber!!,
                mapper.toModel(entity.pokemonSpeciesEntity!!, factory))
    }
}