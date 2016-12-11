package mb.pokequiz.persistence.mapper

import mb.pokequiz.persistence.entity.PokedexEntity
import mb.pokequiz.web.model.Pokedex

/**
 * Created by mbpeele on 12/11/16.
 */
class PokeDexMapper : Mapper<Pokedex, PokedexEntity> {

    override fun toEntity(model: Pokedex, mapperFactory: MapperFactory): PokedexEntity {
        val entity = PokedexEntity()
        entity.isMainSeries = model.isMainSeries
        entity.name = model.name
        return entity
    }

    override fun toModel(entity: PokedexEntity, mapperFactory: MapperFactory): Pokedex {
        val model = Pokedex()
        model.name = entity.name
        model.isMainSeries = entity.isMainSeries
        return model
    }
}