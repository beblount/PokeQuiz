package mb.pokequiz.api.model

/**
 * Created by mbpeele on 12/25/16.
 */
data class VersionGroupDetail(
        val move_learn_method: NamedResource,
        val level_learned_at: Int,
        val version_group: NamedResource) {
}