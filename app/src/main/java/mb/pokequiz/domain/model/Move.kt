package mb.pokequiz.domain.model

/**
 * Created by mbpeele on 12/25/16.
 */
data class Move(
        val move: NamedResource,
        val version_group_details: List<VersionGroupDetail>) {
}