package mb.pokequiz.persistence.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class PokemonSpecies : RealmObject() {

    @SerializedName("name")
    var name: String ?= null
    @SerializedName("url")
    var url: String ?= null
}