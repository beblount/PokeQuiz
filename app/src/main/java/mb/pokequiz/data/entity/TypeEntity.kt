package mb.pokequiz.data.entity

import io.realm.RealmObject
import mb.pokequiz.data.json.NamedResource

/**
 * Created by mbpeele on 1/1/17.
 */
open class TypeEntity : RealmObject() {

    var slot : Int ?= null
    var type : NamedResourceEntity ?= null
}