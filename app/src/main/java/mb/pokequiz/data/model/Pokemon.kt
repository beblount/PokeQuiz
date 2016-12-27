package mb.pokequiz.data.model

/**
 * Created by mbpeele on 12/25/16.
 */
data class Pokemon(
        val id: Int,
        val name: String,
        val base_experience: Int,
        val height: Int,
        val is_default: Boolean,
        val order: Int,
        val weight: Int,
        val location_area_encounters: String,
        val sprites: Sprite,
        val species: NamedResource,
        val forms: List<NamedResource>,
        val game_indices: List<GameIndex>,
        val stats: List<Stat>) {
}