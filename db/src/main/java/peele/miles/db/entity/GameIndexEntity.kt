package peele.miles.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class GameIndexEntity : RealmObject() {

    var game_index: Int ?= null
    var version: NamedResourceEntity ?= null

}