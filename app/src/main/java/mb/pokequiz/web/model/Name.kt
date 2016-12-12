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
     * *     The languageEntity
     */
    /**

     * @param languageEntity
     * *     The languageEntity
     */
    @SerializedName("languageEntity")
    @Expose
    var language: Language? = null

}
