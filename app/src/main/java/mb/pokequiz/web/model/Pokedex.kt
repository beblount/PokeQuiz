package mb.pokequiz.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pokedex {

    /**

     * @return
     * *     The id
     */
    /**

     * @param id
     * *     The id
     */
    @SerializedName("id")
    @Expose
    var id: Int? = null
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
     * *     The isMainSeries
     */
    /**

     * @param isMainSeries
     * *     The is_main_series
     */
    @SerializedName("is_main_series")
    @Expose
    var isMainSeries: Boolean? = null
    /**

     * @return
     * *     The descriptions
     */
    /**

     * @param descriptions
     * *     The descriptions
     */
    @SerializedName("descriptions")
    @Expose
    var descriptions: List<Description>? = null
    /**

     * @return
     * *     The names
     */
    /**

     * @param names
     * *     The names
     */
    @SerializedName("names")
    @Expose
    var names: List<Name>? = null
    /**

     * @return
     * *     The pokemonEntries
     */
    /**

     * @param pokemonEntries
     * *     The pokemon_entries
     */
    @SerializedName("pokemon_entries")
    @Expose
    var pokemonEntries: List<PokemonEntry>? = null
    /**

     * @return
     * *     The region
     */
    /**

     * @param region
     * *     The region
     */
    @SerializedName("region")
    @Expose
    var region: Region? = null
    /**

     * @return
     * *     The versionGroups
     */
    /**

     * @param versionGroups
     * *     The version_groups
     */
    @SerializedName("version_groups")
    @Expose
    var versionGroups: List<VersionGroup>? = null

}
