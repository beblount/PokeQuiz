package mb.pokequiz.persistence.entity

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class NameEntity : RealmObject() {

    var name: String? = null
    var languageEntity: LanguageEntity? = null
}
