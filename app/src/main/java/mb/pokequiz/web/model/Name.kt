package mb.pokequiz.web.model

import com.squareup.moshi.Json

class Name {

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
     * *     The language
     */
    /**

     * @param language
     * *     The language
     */
    @Json(name = "language")
    var language: Language? = null

}
