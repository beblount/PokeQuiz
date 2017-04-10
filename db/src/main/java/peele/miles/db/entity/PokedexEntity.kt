package peele.miles.db.entity

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Created by mbpeele on 12/11/16.
 */
open class PokedexEntity : RealmObject() {

    @PrimaryKey
    var id: Int? = null
    var name: String? = null
    var isMainSeries: Boolean? = null
    var descriptions: RealmList<DescriptionEntity>? = null
    var names: RealmList<NameEntity>? = null
    var pokemon_entries: RealmList<PokemonEntryEntity>? = null
    var region: NamedResourceEntity? = null
    var version_groups: RealmList<NamedResourceEntity>? = null

    override fun toString(): String {
        return "PokedexEntity(name=$name, isMainSeries=$isMainSeries, descriptions=$descriptions, names=$names, pokemon_entries=$pokemon_entries, region=$region, version_groups=$version_groups)"
    }


}