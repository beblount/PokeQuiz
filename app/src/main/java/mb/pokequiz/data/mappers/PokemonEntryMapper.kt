package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.PokemonEntryEntity
import mb.pokequiz.data.json.PokemonEntry

/**
 * Created by mbpeele on 12/26/16.
 */
object PokemonEntryMapper {

    fun toEntity(model: PokemonEntry): PokemonEntryEntity {
        val entity = PokemonEntryEntity()
        entity.entryNumber = model.entry_number
        entity.pokemon_species = NamedResourceMapper.toEntity(model.pokemon_species)
        return entity
    }

    fun toModel(entity: PokemonEntryEntity): PokemonEntry {
        return PokemonEntry(entity.entryNumber!!,
                NamedResourceMapper.toModel(entity.pokemon_species!!))
    }
}