package mb.pokequiz.data.entity

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class MoveEntity : RealmObject() {

    var move: NamedResourceEntity ?= null
    var version_group_details: RealmList<VersionGroupDetailEntity> ?= null
}