package mb.pokequiz.persistence.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class PokemonEntryEntity : RealmObject() {

    var entryNumber: Int ?= null
    var pokemonSpeciesEntity: PokemonSpeciesEntity?= null
}
