package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.awt.geom.Area

data class PalParkEncounter(
    @SerializedName("rate")
    @Expose
    var rate: Int? = null,
    @SerializedName("base_score")
    @Expose
    var baseScore: Int? = null,
    @SerializedName("area")
    @Expose
    var area: Area? = null) {

}