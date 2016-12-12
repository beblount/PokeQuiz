package mb.pokequiz.persistence.entity

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class DescriptionEntity : RealmObject() {

    var description: String? = null
    var languageEntity: LanguageEntity? = null
}
