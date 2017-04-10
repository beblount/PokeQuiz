package peele.miles.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class StatEntity : RealmObject() {

    var base_stat: Int ?= null
    var effort: Int ?= null
    var stat: NamedResourceEntity ?= null
}