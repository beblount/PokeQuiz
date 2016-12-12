package mb.pokequiz.persistence.mapper

import io.realm.RealmObject
import mb.pokequiz.persistence.entity.PokedexEntity
import mb.pokequiz.web.model.Pokedex

/**
 * Created by mbpeele on 12/11/16.
 */

class PokeDexMapper : Mapper<Pokedex> {

    override fun toEntity(model: Pokedex, mapperFactory: MapperFactory): PokedexEntity {
        val entity = PokedexEntity()
        entity.name = model.name
        entity.isMainSeries = model.isMainSeries
        entity.id = model.id
        return entity
    }

    override fun fromEntity(realmObject: RealmObject, mapperFactory: MapperFactory): Pokedex {
        val model = Pokedex()
        val entity = realmObject as PokedexEntity
        model.name = entity.name
        model.isMainSeries = entity.isMainSeries
        model.id = entity.id
        return model
    }
}
