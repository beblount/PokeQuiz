package mb.pokequiz.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Species(
    @SerializedName("capture_rate")
    @Expose
    var captureRate: Int? = null,
    @SerializedName("habitat")
    @Expose
    var habitat: NamedResource? = null,
    @SerializedName("color")
    @Expose
    var color: NamedResource? = null,
    @SerializedName("forms_switchable")
    @Expose
    var formsSwitchable: Boolean? = null,
    @SerializedName("shape")
    @Expose
    var shape: NamedResource? = null,
    @SerializedName("names")
    @Expose
    var names: List<Name>? = null,
    @SerializedName("id")
    @Expose
    var id: Int? = null,
    @SerializedName("egg_groups")
    @Expose
    var eggGroups: List<NamedResource>? = null,
    @SerializedName("base_happiness")
    @Expose
    var baseHappiness: Int? = null,
    @SerializedName("generation")
    @Expose
    var generation: NamedResource? = null,
    @SerializedName("flavor_text_entries")
    @Expose
    var flavorTextEntries: List<FlavorTextEntry>? = null,
    @SerializedName("growth_rate")
    @Expose
    var growthRate: NamedResource? = null,
    @SerializedName("hatch_counter")
    @Expose
    var hatchCounter: Int? = null,
    @SerializedName("genera")
    @Expose
    var genera: List<Genera>? = null,
    @SerializedName("evolves_from_species")
    @Expose
    var evolvesFromSpecies: String? = null,
    @SerializedName("varieties")
    @Expose
    var varieties: List<Variety>? = null,
    @SerializedName("name")
    @Expose
    var name: String? = null,
    @SerializedName("evolution_chain")
    @Expose
    var evolutionChain: EvolutionChain? = null,
    @SerializedName("has_gender_differences")
    @Expose
    var hasGenderDifferences: Boolean? = null,
    @SerializedName("is_baby")
    @Expose
    var isBaby: Boolean? = null,
    @SerializedName("gender_rate")
    @Expose
    var genderRate: Int? = null,
    @SerializedName("pal_park_encounters")
    @Expose
    var palParkEncounters: List<PalParkEncounter>? = null,
    @SerializedName("order")
    @Expose
    var order: Int? = null,
    @SerializedName("pokedex_numbers")
    @Expose
    var pokedexNumbers: List<PokedexNumber>? = null) {

}
