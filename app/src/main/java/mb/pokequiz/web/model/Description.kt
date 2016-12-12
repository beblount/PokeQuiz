package mb.pokequiz.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Description {

    /**

     * @return
     * *     The description
     */
    /**

     * @param description
     * *     The description
     */
    @SerializedName("description")
    @Expose
    var description: String? = null
    /**

     * @return
     * *     The language
     */
    /**

     * @param language
     * *     The language
     */
    @SerializedName("language")
    @Expose
    var language: Language? = null

}
