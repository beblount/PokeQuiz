package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PokedexNumber(
    @SerializedName("entry_number")
    @Expose
    var entryNumber: Int? = null,
    @SerializedName("pokedex")
    @Expose
    var pokedex: NamedResource? = null) {

}