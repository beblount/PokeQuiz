package mb.pokequiz.data.mapper

import io.realm.RealmList
import mb.pokequiz.data.entity.*
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.PokeDexMapper
import mb.pokequiz.data.json.*
import java.util.*

/**
 * Created by mbpeele on 12/26/16.
 */
class PokeDexMapperTest : MapperTest<Pokedex, PokedexEntity>() {

    override fun createMapper(): Mapper<Pokedex, PokedexEntity> {
        return PokeDexMapper()
    }

    override fun createModel(): Pokedex {
        var size = randomInt()
        val names = ArrayList<Name>(size)
        for (i in 0 until size) {
            val name = Name(randomString(), createNamedResource())
            names.add(name)
        }

        size = randomInt()
        val descriptions = ArrayList<Description>(size)
        for (i in 0 until size) {
            val description = Description(randomString(), createNamedResource())
            descriptions.add(description)
        }

        size = randomInt()
        val entries = ArrayList<PokemonEntry>(size)
        for (i in 0 until size) {
            val entry = PokemonEntry(randomInt(), createNamedResource())
            entries.add(entry)
        }

        size = randomInt()
        val resources = ArrayList<NamedResource>(size)
        for (i in 0 until size) {
            resources.add(createNamedResource())
        }

        return Pokedex(randomInt(), randomString(), randomBool(), createNamedResource(),
                names, descriptions, entries, resources)
    }

    override fun createEntity(): PokedexEntity {
        val entity = PokedexEntity()
        entity.id = randomInt()
        entity.name = randomString()
        entity.isMainSeries = randomBool()
        entity.region = createNamedResourceEntity()

        var size = randomInt()
        val names = RealmList<NameEntity>()
        for (i in 0 until size) {
            val name = NameEntity()
            name.name = randomString()
            name.language = createNamedResourceEntity()
            names.add(name)
        }
        entity.names = names

        size = randomInt()
        val descriptions = RealmList<DescriptionEntity>()
        for (i in 0 until size) {
            val description = DescriptionEntity()
            description.description = randomString()
            description.language = createNamedResourceEntity()
            descriptions.add(description)
        }
        entity.descriptions = descriptions

        size = randomInt()
        val entries = RealmList<PokemonEntryEntity>()
        for (i in 0 until size) {
            val entry = PokemonEntryEntity()
            entry.entryNumber = randomInt()
            entry.pokemon_species = createNamedResourceEntity()
            entries.add(entry)
        }
        entity.pokemon_entries = entries

        size = randomInt()
        val resources = RealmList<NamedResourceEntity>()
        for (i in 0 until size) {
            resources.add(createNamedResourceEntity())
        }
        entity.version_groups = resources

        return entity
    }
}