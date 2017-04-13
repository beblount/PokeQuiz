package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Variety(
    @SerializedName("is_default")
    @Expose
    var isDefault: Boolean? = null,
    @SerializedName("pokemon")
    @Expose
    var pokemon: NamedResource? = null) {

}