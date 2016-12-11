package mb.pokequiz.web.model

import com.squareup.moshi.Json

class Pokedex {

    /**

     * @return
     * *     The id
     */
    /**

     * @param id
     * *     The id
     */
    @Json(name = "id")
    var id: Int? = null
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
     * *     The isMainSeries
     */
    /**

     * @param isMainSeries
     * *     The is_main_series
     */
    @Json(name = "is_main_series")
    var isMainSeries: Boolean? = null
    /**

     * @return
     * *     The descriptions
     */
    /**

     * @param descriptions
     * *     The descriptions
     */
    @Json(name = "descriptions")
    var descriptions: List<Description>? = null
    /**

     * @return
     * *     The names
     */
    /**

     * @param names
     * *     The names
     */
    @Json(name = "names")
    var names: List<Name>? = null
    /**

     * @return
     * *     The pokemonEntries
     */
    /**

     * @param pokemonEntries
     * *     The pokemon_entries
     */
    @Json(name = "pokemon_entries")
    var pokemonEntries: List<PokemonEntry>? = null
    /**

     * @return
     * *     The region
     */
    /**

     * @param region
     * *     The region
     */
    @Json(name = "region")
    var region: Region? = null
    /**

     * @return
     * *     The versionGroups
     */
    /**

     * @param versionGroups
     * *     The version_groups
     */
    @Json(name = "version_groups")
    var versionGroups: List<VersionGroup>? = null

}
