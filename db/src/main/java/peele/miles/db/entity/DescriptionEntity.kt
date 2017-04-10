package peele.miles.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class DescriptionEntity : RealmObject() {

    var description: String? = null
    var language: NamedResourceEntity? = null
}
