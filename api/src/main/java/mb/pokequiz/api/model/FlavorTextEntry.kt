package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class FlavorTextEntry(
    @SerializedName("version")
    @Expose
    var version: NamedResource? = null,
    @SerializedName("flavor_text")
    @Expose
    var flavorText: String? = null,
    @SerializedName("language")
    @Expose
    var language: NamedResource? = null) {

}