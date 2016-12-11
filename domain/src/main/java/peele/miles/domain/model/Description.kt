package peele.miles.domain.model

import com.squareup.moshi.Json

class Description {

    /**

     * @return
     * *     The description
     */
    /**

     * @param description
     * *     The description
     */
    @Json(name = "description")
    var description: String? = null
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
