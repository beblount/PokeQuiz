package mb.pokequiz.data.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/27/16.
 */
open class ProfileEntity : RealmObject() {

    var name : String? = null
    var avatarUrl : String? = null
}