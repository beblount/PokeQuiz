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

    object PokemonMapper : Mapper<Pokemon, PokemonEntity> {
        override fun toModel(entity: PokemonEntity): Pokemon {
            val forms = modelList(entity.forms, NamedResourceMapper)
            val indices = modelList(entity.game_indices, GameIndexMapper)
            val stats = modelList(entity.stats, StatMapper)
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
                    forms,
                    indices,
                    species,
                    stats)
        }

        override fun toEntity(model: Pokemon): PokemonEntity {
            val entity = PokemonEntity()
            entity.id = model.id
            entity.base_experience = model.base_experience
            entity.height = model.height
            entity.is_default = model.is_default
            entity.location_area_encounters = model.location_area_encounters
            entity.name = model.name
            entity.order = model.order
            entity.weight = model.weight
            entity.forms = entityList(model.forms, NamedResourceMapper)
            entity.game_indices = entityList(model.game_indices, GameIndexMapper)
            entity.stats = entityList(model.stats, StatMapper)
            entity.species = NamedResourceMapper.toEntity(model.species)
            entity.sprite = SpriteMapper.toEntity(model.sprites)
            return entity
        }

    }

    object SpriteMapper : Mapper<Sprite, SpriteEntity> {
        override fun toModel(entity: SpriteEntity): Sprite {
            return Sprite(entity.back_female,
                    entity.back_shiny_female,
                    entity.back_default,
                    entity.front_female,
                    entity.front_shiny_female,
                    entity.back_shiny,
                    entity.front_default,
                    entity.front_shiny)
        }

        override fun toEntity(model: Sprite): SpriteEntity {
            val entity = SpriteEntity()
            entity.back_default = model.back_default
            entity.back_female = model.back_female
            entity.back_shiny = model.back_shiny
            entity.back_shiny_female = model.back_shiny_female
            entity.front_default = model.front_default
            entity.front_female = model.front_female
            entity.front_shiny = model.front_shiny
            entity.front_shiny_female = model.front_shiny_female
            return entity
        }

    }

    object StatMapper : Mapper<Stat, StatEntity> {
        override fun toModel(entity: StatEntity): Stat {
            return Stat(entity.effort!!,
                    entity.base_stat!!,
                    NamedResourceMapper.toModel(entity.stat!!))
        }

        override fun toEntity(model: Stat): StatEntity {
            val entity = StatEntity()
            entity.effort = model.effort
            entity.base_stat = model.base_stat
            entity.stat = NamedResourceMapper.toEntity(model.stat)
            return entity
        }

    }

    object GameIndexMapper : Mapper<GameIndex, GameIndexEntity> {
        override fun toModel(entity: GameIndexEntity): GameIndex {
            return GameIndex(entity.game_index!!,
                    NamedResourceMapper.toModel(entity.version!!))
        }

        override fun toEntity(model: GameIndex): GameIndexEntity {
            val entity = GameIndexEntity()
            entity.game_index = model.game_index
            entity.version = NamedResourceMapper.toEntity(model.version)
            return entity
        }

    }

    object PokeDexMapper : Mapper<Pokedex, PokedexEntity> {
        override fun toEntity(model: Pokedex): PokedexEntity {
            val entity = PokedexEntity()

            val names = entityList(model.names, NameMapper)
            val descriptions = entityList(model.descriptions, DescriptionMapper)
            val pokemonEntries = entityList(model.pokemon_entries, PokemonEntryMapper)
            val versionGroups = entityList(model.version_groups, NamedResourceMapper)

            entity.id = model.id
            entity.isMainSeries = model.is_main_series
            entity.name = model.name
            entity.descriptions = descriptions
            entity.nameEntities = names
            entity.pokemonEntryEntities = pokemonEntries
            entity.versionGroups = versionGroups
            entity.region = if (model.region == null) null else NamedResourceMapper.toEntity(model.region)

            return entity
        }

        override fun toModel(entity: PokedexEntity): Pokedex {
            val names = modelList(entity.nameEntities, NameMapper)
            val descriptions = modelList(entity.descriptions, DescriptionMapper)
            val pokemonEntries = modelList(entity.pokemonEntryEntities, PokemonEntryMapper)
            val versionGroups = modelList(entity.versionGroups, NamedResourceMapper)
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

    object PokemonEntryMapper : Mapper<PokemonEntry, PokemonEntryEntity> {
        override fun toEntity(model: PokemonEntry): PokemonEntryEntity {
            val entity = PokemonEntryEntity()
            entity.entryNumber = model.entry_number
            entity.pokemonSpeciesEntity = NamedResourceMapper.toEntity(model.pokemon_species)
            return entity
        }

        override fun toModel(entity: PokemonEntryEntity): PokemonEntry {
            return PokemonEntry(entity.entryNumber!!,
                    NamedResourceMapper.toModel(entity.pokemonSpeciesEntity!!))
        }
    }

    object DescriptionMapper : Mapper<Description, DescriptionEntity> {
        override fun toEntity(model: Description): DescriptionEntity {
            val entity = DescriptionEntity()
            entity.languageEntity = NamedResourceMapper.toEntity(model.language)
            entity.description = model.description
            return entity
        }

        override fun toModel(entity: DescriptionEntity): Description {
            return Description(entity.description!!,
                    NamedResourceMapper.toModel(entity.languageEntity!!))
        }
    }

    object NameMapper : Mapper<Name, NameEntity> {
        override fun toEntity(model: Name): NameEntity {
            val entity = NameEntity()
            entity.name = model.name
            entity.languageEntity = NamedResourceMapper.toEntity(model.language)
            return entity
        }

        override fun toModel(entity: NameEntity): Name {
            return Name(entity.name!!,
                    NamedResourceMapper.toModel(entity.languageEntity!!))
        }
    }

    object NamedResourceMapper : Mapper<NamedResource, NamedResourceEntity> {
        override fun toModel(entity: NamedResourceEntity): NamedResource {
            return NamedResource(entity.name!!, entity.url!!)
        }

        override fun toEntity(model: NamedResource): NamedResourceEntity {
            val entity = NamedResourceEntity()
            entity.name = model.name
            entity.url = model.url
            return entity
        }

    }

    fun <E, T> modelList(list: List<T>?, mapper: Mapper<E, T>) : List<E> {
        if (list == null) {
            return ArrayList()
        } else {
            return list.map { mapper.toModel(it) }
        }
    }

    fun <E, T : RealmModel?> entityList(list: List<E>?, mapper: Mapper<E, T>) : RealmList<T> {
        if (list == null) {
            return RealmList()
        } else {
            val realmList = RealmList<T>()
            val entities = list.map { mapper.toEntity(it) }
            realmList.addAll(entities)
            return realmList
        }
    }
}