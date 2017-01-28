package mb.pokequiz.data.mappers

import mb.pokequiz.data.entity.GameIndexEntity
import mb.pokequiz.data.json.GameIndex

/**
 * Created by mbpeele on 12/26/16.
 */
object GameIndexMapper {

    fun toModel(entity: GameIndexEntity): GameIndex {
        return GameIndex(entity.game_index!!,
                NamedResourceMapper.toModel(entity.version!!))
    }

    fun toEntity(model: GameIndex): GameIndexEntity {
        val entity = GameIndexEntity()
        entity.game_index = model.game_index
        entity.version = NamedResourceMapper.toEntity(model.version)
        return entity
    }
}