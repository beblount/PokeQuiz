package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class EvolutionChain(
    @SerializedName("url")
    @Expose
    var url: String? = null) {

}