package mb.pokequiz.data.json

/**
 * Created by mbpeele on 12/25/16.
 */
data class Move(
        val move: NamedResource,
        val version_group_details: List<VersionGroupDetail>) {
}