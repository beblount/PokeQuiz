package mb.pokequiz.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Name {

    /**

     * @return
     * *     The name
     */
    /**

     * @param name
     * *     The name
     */
    @SerializedName("name")
    @Expose
    var name: String? = null
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
