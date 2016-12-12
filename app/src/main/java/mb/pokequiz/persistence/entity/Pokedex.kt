package mb.pokequiz.persistence.entity

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject

/**
 * Created by mbpeele on 12/11/16.
 */
open class Pokedex : RealmObject() {

    @SerializedName("name")
    var name: String? = null
    @SerializedName("is_main_series")
    var isMainSeries: Boolean? = null
    @SerializedName("descriptions")
    var descriptions: RealmList<Description>? = null
    @SerializedName("names")
    var names: RealmList<Name>? = null
    @SerializedName("pokemon_entries")
    var pokemonEntries: RealmList<PokemonEntry>? = null
    @SerializedName("region")
    var region: Region? = null
    @SerializedName("version_groups")
    var versionGroups: RealmList<VersionGroup>? = null

    override fun toString(): String {
        return "Pokedex(name=$name, isMainSeries=$isMainSeries, descriptions=$descriptions, names=$names, pokemonEntries=$pokemonEntries, region=$region, versionGroups=$versionGroups)"
    }


}