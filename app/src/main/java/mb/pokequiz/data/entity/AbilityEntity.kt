package mb.pokequiz.data.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class AbilityEntity : RealmObject() {

    var is_hidden: Boolean ?= null
    var slot: Int ?= null
    var ability: NamedResourceEntity ?= null

}