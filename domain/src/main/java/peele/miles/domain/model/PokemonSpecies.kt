package peele.miles.domain.model

import com.squareup.moshi.Json

class PokemonSpecies {

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
