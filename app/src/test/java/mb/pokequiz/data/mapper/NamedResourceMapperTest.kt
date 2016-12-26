package mb.pokequiz.data.mapper

import mb.pokequiz.data.ApplicationTestCase
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.mappers.NamedResourceMapper
import mb.pokequiz.data.mappers.PokeMapperFactory
import mb.pokequiz.data.model.NamedResource
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test

/**
 * Created by mbpeele on 12/26/16.
 */
class NamedResourceMapperTest : ApplicationTestCase() {

    val fakeName : String = "SOMETHING ANYTHING BRO"
    val fakeUrl : String = "http://mileswinsatlife.com"

    lateinit var factory: MapperFactory
    lateinit var mapper : NamedResourceMapper

    @Before
    fun setup() {
        factory = PokeMapperFactory()
        mapper = NamedResourceMapper()
    }

    @Test
    fun mapModelToEntity() {
        val model = NamedResource(fakeName, fakeUrl)
        val entity = mapper.toEntity(model, factory)

        assertThat(entity.name, `is`(model.name))
        assertThat(entity.url, `is`(model.url))
    }

    @Test
    fun mapEntityToModel() {
        val entity = NamedResourceEntity()
        entity.name = fakeName
        entity.url = fakeUrl
        val model = mapper.toModel(entity, factory)

        assertThat(model.name, `is`(entity.name))
        assertThat(model.url, `is`(entity.url))
    }

}