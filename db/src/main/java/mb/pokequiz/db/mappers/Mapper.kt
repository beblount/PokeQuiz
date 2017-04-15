package mb.pokequiz.db.mappers

/**
 * Created by mbpeele on 4/15/17.
 */
interface Mapper<Model, Entity> {

    fun toModel(entity: Entity) : Model

    fun toEntity(model: Model) : Entity
}