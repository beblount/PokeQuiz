package mb.pokequiz.persistence.entity

import com.google.gson.annotations.SerializedName
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class PokemonEntry : RealmObject() {

    @SerializedName("entry_number")
    var entryNumber: Int ?= null
    @SerializedName("pokemon_species")
    var pokemonSpecies: PokemonSpecies?= null
}
