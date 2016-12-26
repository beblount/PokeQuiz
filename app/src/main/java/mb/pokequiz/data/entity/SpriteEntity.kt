package mb.pokequiz.data.entity

import io.realm.RealmObject

/**
 * Created by mbpeele on 12/25/16.
 */
open class SpriteEntity : RealmObject() {

    var back_female: String ?= null
    var back_shiny_female: String ?= null
    var back_default: String ?= null
    var front_female: String ?= null
    var front_shiny_female: String ?= null
    var back_shiny: String ?= null
    var front_default: String ?= null
    var front_shiny: String ?= null

}