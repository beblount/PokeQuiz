package mb.pokequiz.data.model

import com.squareup.moshi.Json
import mb.pokequiz.data.model.Language

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
