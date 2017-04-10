package mb.pokequiz.api.model

/**
 * Created by mbpeele on 12/25/16.
 */
data class Sprite(
        val back_female: String?,
        val back_shiny_female: String?,
        val back_default: String?,
        val front_female: String?,
        val front_shiny_female: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?) {

    fun getFront(): String? {
        return firstNonNull(front_female, front_shiny_female, front_default, front_shiny)
    }

    private fun firstNonNull(vararg args: String?) : String? {
        return args.firstOrNull { it != null }
    }
}