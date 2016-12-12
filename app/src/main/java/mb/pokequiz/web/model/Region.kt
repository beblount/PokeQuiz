package mb.pokequiz.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Region {

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
     * *     The url
     */
    /**

     * @param url
     * *     The url
     */
    @SerializedName("url")
    @Expose
    var url: String? = null

}
