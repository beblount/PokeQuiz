package mb.pokequiz.data.mapper

import io.realm.RealmObject
import junit.framework.Assert
import mb.pokequiz.data.ApplicationTestCase
import mb.pokequiz.data.mappers.Mapper
import mb.pokequiz.data.mappers.MapperFactory
import mb.pokequiz.data.mappers.PokeMapperFactory
import org.junit.Before
import org.junit.Test

/**
 * Created by mbpeele on 12/26/16.
 */
abstract class MapperTest<Model, Entity : RealmObject> : ApplicationTestCase() {

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

        reflectionTest(model as Any, entity)
    }

    @Test
    fun mapEntityToModel() {
        val entity = createEntity()
        val model = mapper.toModel(entity, factory)

        reflectionTest(model as Any, entity)
    }

    private fun reflectionTest(pojo: Any, realmObject: RealmObject) {
        val pojoClass = pojo.javaClass
        val pojoFields = pojoClass.declaredFields

        val realmClass = realmObject.javaClass
        val realmFields = realmClass.declaredFields

        Assert.assertEquals(pojoFields.size, realmFields.size)

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

                    val pojoFieldValue = pojoField.get(pojo)
                    val realmFieldValue = realmField.get(realmObject)

                    Assert.assertEquals(pojoFieldValue, realmFieldValue)
                }
            }
        }
    }
}