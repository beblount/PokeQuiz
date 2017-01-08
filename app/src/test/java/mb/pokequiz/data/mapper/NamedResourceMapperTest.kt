package mb.pokequiz.data.mapper

import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.NamedResourceMapper
import mb.pokequiz.data.json.NamedResource

/**
 * Created by mbpeele on 12/26/16.
 */
class NamedResourceMapperTest : MapperTest<NamedResource, NamedResourceEntity>() {

    override fun createMapper(): Mapper<NamedResource, NamedResourceEntity> {
       return NamedResourceMapper()
    }

    override fun createModel(): NamedResource {
        return createNamedResource()
    }

    override fun createEntity(): NamedResourceEntity {
        return createNamedResourceEntity()
    }

}