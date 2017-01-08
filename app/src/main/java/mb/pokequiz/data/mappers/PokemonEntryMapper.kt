package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.entity.PokemonEntryEntity
import mb.pokequiz.data.json.NamedResource
import mb.pokequiz.data.json.PokemonEntry

/**
 * Created by mbpeele on 12/26/16.
 */
open class PokemonEntryMapper : Mapper<PokemonEntry, PokemonEntryEntity> {
    override fun toEntity(model: PokemonEntry, factory: MapperFactory): PokemonEntryEntity {
        val entity = PokemonEntryEntity()
        entity.entryNumber = model.entry_number
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        entity.pokemon_species = mapper.toEntity(model.pokemon_species, factory)
        return entity
    }

    override fun toModel(entity: PokemonEntryEntity, factory: MapperFactory): PokemonEntry {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        return PokemonEntry(entity.entryNumber!!,
                mapper.toModel(entity.pokemon_species!!, factory))
    }
}