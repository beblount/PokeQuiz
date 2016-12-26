package mb.pokequiz.data.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class NameEntity : RealmObject() {

    var name: String? = null
    var language: NamedResourceEntity? = null
}
