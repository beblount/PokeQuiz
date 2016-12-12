package mb.pokequiz.web.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PokemonEntry {

    /**

     * @return
     * *     The entryNumber
     */
    /**

     * @param entryNumber
     * *     The entry_number
     */
    @SerializedName("entry_number")
    @Expose
    var entryNumber: Int? = null
    /**

     * @return
     * *     The pokemonSpecies
     */
    /**

     * @param pokemonSpecies
     * *     The pokemon_species
     */
    @SerializedName("pokemon_species")
    @Expose
    var pokemonSpecies: PokemonSpecies? = null

}
