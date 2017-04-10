package mb.pokequiz.api.model

/**
 * Created by mbpeele on 1/1/17.
 */
data class Hint(val text: String, val hintType: HintType) {

    enum class HintType {
        ABILITY,
        TYPE
    }
}