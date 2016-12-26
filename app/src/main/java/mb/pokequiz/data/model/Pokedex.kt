package mb.pokequiz.data.model

data class Pokedex (
        val id: Int,
        val name: String,
        val is_main_series: Boolean,
        val region: NamedResource?,
        val names: List<Name>,
        val descriptions: List<Description>,
        val pokemon_entries: List<PokemonEntry>,
        val version_groups: List<NamedResource>) {

}
