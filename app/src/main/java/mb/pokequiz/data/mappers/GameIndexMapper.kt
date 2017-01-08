package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.GameIndexEntity
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.json.GameIndex
import mb.pokequiz.data.json.NamedResource

/**
 * Created by mbpeele on 12/26/16.
 */
open class GameIndexMapper : Mapper<GameIndex, GameIndexEntity> {
    override fun toModel(entity: GameIndexEntity, factory: MapperFactory): GameIndex {
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        return GameIndex(entity.game_index!!,
                mapper.toModel(entity.version!!, factory))
    }

    override fun toEntity(model: GameIndex, factory: MapperFactory): GameIndexEntity {
        val entity = GameIndexEntity()
        entity.game_index = model.game_index
        val mapper = factory.create<NamedResource, NamedResourceEntity>(NamedResource::class)
        entity.version = mapper.toEntity(model.version, factory)
        return entity
    }
}