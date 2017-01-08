package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.PokemonEntryEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.PokemonEntryMapper
import mb.pokequiz.data.json.PokemonEntry

/**
 * Created by mbpeele on 12/27/16.
 */
class PokemonEntryMapperTest : MapperTest<PokemonEntry, PokemonEntryEntity>() {
    override fun createMapper(): Mapper<PokemonEntry, PokemonEntryEntity> {
        return PokemonEntryMapper()
    }

    override fun createModel(): PokemonEntry {
        return PokemonEntry(randomInt(), createNamedResource())
    }

    override fun createEntity(): PokemonEntryEntity {
        val entity = PokemonEntryEntity()
        entity.entryNumber = randomInt()
        entity.pokemon_species = createNamedResourceEntity()
        return entity
    }
}