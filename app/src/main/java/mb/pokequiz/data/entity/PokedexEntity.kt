package mb.pokequiz.data.entity

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
    var nameEntities: RealmList<NameEntity>? = null
    var pokemonEntryEntities: RealmList<PokemonEntryEntity>? = null
    var region: NamedResourceEntity? = null
    var versionGroups: RealmList<NamedResourceEntity>? = null

    override fun toString(): String {
        return "PokedexEntity(name=$name, isMainSeries=$isMainSeries, descriptions=$descriptions, nameEntities=$nameEntities, pokemonEntryEntities=$pokemonEntryEntities, region=$region, versionGroups=$versionGroups)"
    }


}