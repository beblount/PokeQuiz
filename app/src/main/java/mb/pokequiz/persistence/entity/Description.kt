package mb.pokequiz.persistence.entity

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class Description : RealmObject() {

    @SerializedName("description")
    var description: String? = null
    @SerializedName("language")
    var language: Language? = null
}
