package mb.pokequiz.data.model

import io.realm.RealmList
import io.realm.RealmModel
import mb.pokequiz.data.entity.*
import java.util.*

/**
 * Created by mbpeele on 12/25/16.
 */
interface Mapper<Model, Entity> {

    fun toModel(entity: Entity) : Model

    fun toEntity(model: Model) : Entity

    object PokeDexMapper : Mapper<Pokedex, PokedexEntity> {
        override fun toEntity(model: Pokedex): PokedexEntity {
            val entity = PokedexEntity()

            val names = model.names.entityList(NameMapper)
            val descriptions = model.descriptions.entityList(DescriptionMapper)
            val pokemonEntries = model.pokemon_entries.entityList(PokemonEntryMapper)
            val versionGroups = model.version_groups.entityList(VersionGroupMapper)

            entity.id = model.id
            entity.isMainSeries = model.is_main_series
            entity.name = model.name
            entity.descriptions = descriptions
            entity.nameEntities = names
            entity.pokemonEntryEntities = pokemonEntries
            entity.versionGroups = versionGroups
            entity.region = if (model.region == null) null else RegionMapper.toEntity(model.region)

            return entity
        }

        override fun toModel(entity: PokedexEntity): Pokedex {
            val names = entity.nameEntities!!.modelList(NameMapper)
            val descriptions = entity.descriptions!!.modelList(DescriptionMapper)
            val pokemonEntries = entity.pokemonEntryEntities!!.modelList(PokemonEntryMapper)
            val versionGroups = entity.versionGroups!!.modelList(VersionGroupMapper)
            val region = if (entity.region == null) null else RegionMapper.toModel(entity.region!!)

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

    object RegionMapper : Mapper<Region, RegionEntity> {
        override fun toEntity(model: Region): RegionEntity {
            val entity = RegionEntity()
            if (model == null) {
                return entity
            }

            entity.name = model.name
            entity.url = model.url
            return entity
        }

        override fun toModel(entity: RegionEntity): Region {
            return Region(entity.name!!, entity.url!!)
        }
    }

    object PokemonEntryMapper : Mapper<PokemonEntry, PokemonEntryEntity> {
        override fun toEntity(model: PokemonEntry): PokemonEntryEntity {
            val entity = PokemonEntryEntity()
            entity.entryNumber = model.entry_number
            entity.pokemonSpeciesEntity = PokemonSpeciesMapper.toEntity(model.pokemon_species)
            return entity
        }

        override fun toModel(entity: PokemonEntryEntity): PokemonEntry {
            return PokemonEntry(entity.entryNumber!!,
                    PokemonSpeciesMapper.toModel(entity.pokemonSpeciesEntity!!))
        }
    }

    object VersionGroupMapper : Mapper<VersionGroup, VersionGroupEntity> {
        override fun toEntity(model: VersionGroup): VersionGroupEntity {
            val entity = VersionGroupEntity()
            entity.name = model.name
            entity.url = model.url
            return entity
        }

        override fun toModel(entity: VersionGroupEntity): VersionGroup {
            return VersionGroup(entity.name!!, entity.url!!)
        }
    }

    object PokemonSpeciesMapper : Mapper<PokemonSpecies, PokemonSpeciesEntity> {
        override fun toEntity(model: PokemonSpecies): PokemonSpeciesEntity {
            val entity = PokemonSpeciesEntity()
            entity.name = model.name
            entity.url = model.url
            return entity
        }

        override fun toModel(entity: PokemonSpeciesEntity): PokemonSpecies {
            return PokemonSpecies(entity.name!!, entity.url!!)
        }
    }

    object DescriptionMapper : Mapper<Description, DescriptionEntity> {
        override fun toEntity(model: Description): DescriptionEntity {
            val entity = DescriptionEntity()
            entity.languageEntity = LanguageMapper.toEntity(model.language)
            entity.description = model.description
            return entity
        }

        override fun toModel(entity: DescriptionEntity): Description {
            return Description(entity.description!!,
                    LanguageMapper.toModel(entity.languageEntity!!))
        }
    }

    object NameMapper : Mapper<Name, NameEntity> {
        override fun toEntity(model: Name): NameEntity {
            val entity = NameEntity()
            entity.name = model.name
            entity.languageEntity = LanguageMapper.toEntity(model.language)
            return entity
        }

        override fun toModel(entity: NameEntity): Name {
            return Name(entity.name!!,
                    LanguageMapper.toModel(entity.languageEntity!!))
        }
    }

    object LanguageMapper : Mapper<Language, LanguageEntity> {
        override fun toEntity(model: Language): LanguageEntity {
            val entity = LanguageEntity()
            entity.name = model.name
            entity.url = model.url
            return entity
        }

        override fun toModel(entity: LanguageEntity): Language {
            return Language(entity.name!!, entity.url!!)
        }
    }

    fun <E, T> List<T>.modelList(mapper: Mapper<E, T>) : List<E> {
        if (isEmpty()) {
            return ArrayList()
        } else {
            return map { mapper.toModel(it) }
        }
    }

    fun <E, T : RealmModel?> List<E>.entityList(mapper: Mapper<E, T>) : RealmList<T> {
        if (isEmpty()) {
            return RealmList()
        } else {
            val realmList = RealmList<T>()
            val entities = map { mapper.toEntity(it) }
            realmList.addAll(entities)
            return realmList
        }
    }
}