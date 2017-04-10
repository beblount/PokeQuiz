package peele.miles.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 1/1/17.
 */
open class TypeEntity : RealmObject() {

    var slot : Int ?= null
    var type : NamedResourceEntity ?= null
}