package mb.pokequiz.persistence.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class LanguageEntity : RealmObject() {
    var name: String? = null
    var url: String? = null
}
