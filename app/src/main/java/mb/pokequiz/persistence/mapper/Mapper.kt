package mb.pokequiz.persistence.mapper

import io.realm.RealmObject

/**
 * Created by mbpeele on 11/6/16.

 * Implementations of this class must be able to convert between a [Model] and an [Entity]
 * by copying its fields and removing any trace of Database logic.

 * Example:
 * Say you have a BeerEntity that extends from some base
 * DatabaseObject class. An Android component does not need to know about that DatabaseObject class.
 * So, to make sure our components only work with simple POJO's aka [Model]s,
 * you must take that BeerEntity and convert it to a Beer object by copying its fields.
 */
interface Mapper<Model> {

    fun toEntity(model: Model, mapperFactory: MapperFactory):
            /**
             * Creates a new {@Link Entity} with the same fields as the given [Model].
             */
            RealmObject

    fun fromEntity(realmObject: RealmObject, mapperFactory: MapperFactory):
            /**
             * Creates a new {@Link Model} with the same fields as the given [Entity].
             */
            Model

}