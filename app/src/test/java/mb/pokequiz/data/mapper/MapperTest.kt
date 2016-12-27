package mb.pokequiz.data.mapper

import junit.framework.Assert
import mb.pokequiz.data.ApplicationTestCase
import mb.pokequiz.data.entity.NamedResourceEntity
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.mappers.PokeMapperFactory
import mb.pokequiz.data.model.NamedResource
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.*

/**
 * Created by mbpeele on 12/26/16.
 */
abstract class MapperTest<Model, Entity> : ApplicationTestCase() {

    lateinit var factory : MapperFactory
    lateinit var mapper : Mapper<Model, Entity>

    abstract fun createMapper() : Mapper<Model, Entity>

    abstract fun createModel() : Model
    abstract fun createEntity() : Entity

    @Before
    fun setup() {
        factory = PokeMapperFactory()
        mapper = createMapper()
    }

    @Test
    fun mapModelToEntity() {
        val model = createModel()
        val entity = mapper.toEntity(model, factory)

        reflectionTest(model as Any, entity as Any)
    }

    @Test
    fun mapEntityToModel() {
        val entity = createEntity()
        val model = mapper.toModel(entity, factory)

        reflectionTest(model as Any, entity as Any)
    }

    private fun reflectionTest(model: Any, entity: Any) {
        val pojoClass = model.javaClass
        val pojoFields = pojoClass.declaredFields

        val realmClass = entity.javaClass
        val realmFields = realmClass.declaredFields

        val pojoFieldsIterator = pojoFields.iterator()
        while (pojoFieldsIterator.hasNext()) {
            val pojoField = pojoFieldsIterator.next()

            val realmFieldsIterator = realmFields.iterator()
            while (realmFieldsIterator.hasNext()) {
                val realmField = realmFieldsIterator.next()

                val pojoFieldName = pojoField.name
                val realmFieldName = realmField.name
                if (pojoFieldName == realmFieldName) {
                    pojoField.isAccessible = true
                    realmField.isAccessible = true

                    val pojoFieldValue = pojoField.get(model)
                    val realmFieldValue = realmField.get(entity)

                    if (pojoFieldValue.isPrimitive() || pojoFieldValue == null) {
                        Assert.assertEquals(pojoFieldValue, realmFieldValue)
                    } else {
                        if (List::class.java.isAssignableFrom(javaClass)) {
                            val pojoList = pojoFieldValue as List<*>
                            val realmList = realmFieldValue as List<*>

                            val pojoIterator = pojoList.iterator()
                            val realmiterator = realmList.iterator()
                            while (pojoIterator.hasNext() && realmiterator.hasNext()) {
                                val obj1 = pojoIterator.next()
                                val obj2 = realmiterator.next()
                                reflectionTest(obj1!!, obj2!!)
                            }
                        } else {
                            reflectionTest(pojoFieldValue, realmFieldValue)
                        }
                    }
                }
            }
        }
    }

    private fun Any.isPrimitive() : Boolean {
        return this is Number || this is String || this is Boolean
    }

    fun randomInt() : Int {
        return anyInt()
    }

    fun randomString() : String {
        return anyString()
    }

    fun randomBool() : Boolean {
        return anyBoolean()
    }

    fun createNamedResource() : NamedResource {
        return NamedResource(randomString(), randomString())
    }

    fun createNamedResourceEntity() : NamedResourceEntity {
        val entity = NamedResourceEntity()
        entity.name = randomString()
        entity.url = randomString()
        return entity
    }
}