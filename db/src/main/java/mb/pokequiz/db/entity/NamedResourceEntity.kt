package mb.pokequiz.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class NamedResourceEntity : RealmObject() {

    var name: String? = null
    var url: String? = null
}