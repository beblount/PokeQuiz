package mb.pokequiz.persistence.entity

import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class PokedexEntity : RealmObject() {

    var name: String? = null
    var isMainSeries: Boolean? = null
    var descriptions: RealmList<DescriptionEntity>? = null
    var names: RealmList<NameEntity>? = null
    var pokemonEntries: RealmList<PokemonEntryEntity>? = null
    var region: RegionEntity? = null
    var versionGroups: RealmList<VersionGroupEntity>? = null

}