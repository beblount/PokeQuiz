package mb.pokequiz.data.model

import com.squareup.moshi.Json
import mb.pokequiz.data.model.Language

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
