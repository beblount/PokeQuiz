package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.DescriptionEntity
import mb.pokequiz.data.mappers.DescriptionMapper
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.json.Description

/**
 * Created by mbpeele on 12/26/16.
 */
class DescriptionMapperTest : MapperTest<Description, DescriptionEntity>() {

    val description : String = "Long description of object"

    override fun createMapper(): Mapper<Description, DescriptionEntity> {
        return DescriptionMapper()
    }

    override fun createModel(): Description {
        return Description(description, createNamedResource())
    }

    override fun createEntity(): DescriptionEntity {
        val entity =  DescriptionEntity()
        entity.description = description
        entity.language = createNamedResourceEntity()
        return entity
    }
}