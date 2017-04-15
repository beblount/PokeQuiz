package mb.pokequiz.db.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class PokemonEntryEntity : RealmObject() {

    var entryNumber: Int ?= null
    var pokemon_species: NamedResourceEntity?= null
}
