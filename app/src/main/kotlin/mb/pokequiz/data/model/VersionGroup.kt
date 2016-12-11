package mb.pokequiz.data.model

import com.squareup.moshi.Json

class VersionGroup {

    /**

     * @return
     * *     The name
     */
    /**

     * @param name
     * *     The name
     */
    @Json(name = "name")
    var name: String? = null
    /**

     * @return
     * *     The url
     */
    /**

     * @param url
     * *     The url
     */
    @Json(name = "url")
    var url: String? = null

}
