package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.NameEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.NameMapper
import mb.pokequiz.data.model.Name

/**
 * Created by mbpeele on 12/26/16.
 */
class NameMapperTest : MapperTest<Name, NameEntity>() {

    val name = "Jason Witten"

    override fun createMapper(): Mapper<Name, NameEntity> {
        return NameMapper()
    }

    override fun createModel(): Name {
        return Name(name, createNamedResource())
    }

    override fun createEntity(): NameEntity {
        val entity = NameEntity()
        entity.name = name
        entity.language = createNamedResourceEntity()
        return entity
    }
}