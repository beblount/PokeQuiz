package mb.pokequiz.data.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mbpeele on 12/25/16.
 */
open class PokemonEntity : RealmObject() {

    @PrimaryKey
    var id: Int ?= null
    var name: String ?= null
    var base_experience: Int ?= null
    var height: Int ?= null
    var is_default: Boolean ?= null
    var order: Int ?= null
    var weight: Int ?= null
    var location_area_encounters: String ?= null
    var sprite: SpriteEntity ?= null
    var species: NamedResourceEntity ?= null
    var forms: RealmList<NamedResourceEntity> ?= null
    var game_indices: RealmList<GameIndexEntity> ?= null
    var stats: RealmList<StatEntity> ?= null

}