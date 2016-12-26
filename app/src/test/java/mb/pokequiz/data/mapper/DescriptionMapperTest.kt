package mb.pokequiz.data.mapper

import mb.pokequiz.data.ApplicationTestCase
import mb.pokequiz.data.entity.DescriptionEntity
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.mappers.DescriptionMapper
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.mappers.PokeMapperFactory
import mb.pokequiz.data.model.Description
import mb.pokequiz.data.model.NamedResource
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.internal.matchers.apachecommons.ReflectionEquals

/**
 * Created by mbpeele on 12/26/16.
 */
class DescriptionMapperTest : ApplicationTestCase() {

    val description : String = "Pokemon is fun"
    val fakeName : String = "SOMETHING ANYTHING BRO"
    val fakeUrl : String = "http://mileswinsatlife.com"

    lateinit var factory: MapperFactory
    lateinit var mapper : DescriptionMapper

    @Before
    fun setup() {
        factory = PokeMapperFactory()
        mapper = DescriptionMapper()
    }

    @Test
    fun mapModelToEntity() {
        val model = Description(description, NamedResource(fakeName, fakeUrl))
        val entity = mapper.toEntity(model, factory)

        MatcherAssert.assertThat(entity.description, CoreMatchers.`is`(model.description))
        Assert.assertThat(entity.languageEntity, ReflectionEquals(model.language))
    }

    @Test
    fun mapEntityToModel() {
        val entity = DescriptionEntity()
        entity.description = description
        val namedResource = NamedResourceEntity()
        namedResource.name = fakeName
        namedResource.url = fakeUrl
        val model = mapper.toModel(entity, factory)

        MatcherAssert.assertThat(model.description, CoreMatchers.`is`(entity.description))
        Assert.assertThat(model.language, ReflectionEquals(entity.languageEntity))
    }
}