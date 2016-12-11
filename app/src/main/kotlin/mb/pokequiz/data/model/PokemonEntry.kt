package mb.pokequiz.data.model

import com.squareup.moshi.Json
import mb.pokequiz.data.model.PokemonSpecies

class PokemonEntry {

    /**

     * @return
     * *     The entryNumber
     */
    /**

     * @param entryNumber
     * *     The entry_number
     */
    @Json(name = "entry_number")
    var entryNumber: Int? = null
    /**

     * @return
     * *     The pokemonSpecies
     */
    /**

     * @param pokemonSpecies
     * *     The pokemon_species
     */
    @Json(name = "pokemon_species")
    var pokemonSpecies: PokemonSpecies? = null

}
