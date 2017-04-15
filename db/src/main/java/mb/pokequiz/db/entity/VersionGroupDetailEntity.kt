package mb.pokequiz.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class VersionGroupDetailEntity : RealmObject() {

    var move_learn_method: NamedResourceEntity ?= null
    var level_learned_at: Int ?= null
    var version_group: NamedResourceEntity ?= null

}