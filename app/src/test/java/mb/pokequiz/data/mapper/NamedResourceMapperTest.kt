package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.NamedResourceMapper
import mb.pokequiz.data.model.NamedResource

/**
 * Created by mbpeele on 12/26/16.
 */
class NamedResourceMapperTest : MapperTest<NamedResource, NamedResourceEntity>() {

    val fakeName : String = "SOMETHING ANYTHING BRO"
    val fakeUrl : String = "http://mileswinsatlife.com"

    override fun createMapper(): Mapper<NamedResource, NamedResourceEntity> {
       return NamedResourceMapper()
    }

    override fun createModel(): NamedResource {
        return NamedResource(fakeName, fakeUrl)
    }

    override fun createEntity(): NamedResourceEntity {
        val entity = NamedResourceEntity()
        entity.name = fakeName
        entity.url = fakeUrl
        return entity
    }

}