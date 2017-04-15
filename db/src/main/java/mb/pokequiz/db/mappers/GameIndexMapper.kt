package mb.pokequiz.db.mappers

import mb.pokequiz.api.model.GameIndex
import mb.pokequiz.db.entity.GameIndexEntity

/**
 * Created by mbpeele on 12/26/16.
 */
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