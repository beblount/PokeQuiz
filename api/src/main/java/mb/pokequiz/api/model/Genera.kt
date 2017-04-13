package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Genera(
    @SerializedName("genus")
    @Expose
    var genus: String? = null,
    @SerializedName("language")
    @Expose
    var language: NamedResource? = null) {

}
