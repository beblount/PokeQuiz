package mb.pokequiz.data.model

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

    fun getRandom() : String {
        if (back_female == null) {
            if (back_shiny_female == null) {
                if (back_default == null) {
                    if (front_female == null) {
                        if (front_shiny_female == null) {
                            if (back_shiny == null) {
                                if (front_default == null) {
                                    if (front_shiny == null) {
                                        throw RuntimeException()
                                    } else {
                                        return front_shiny
                                    }
                                } else {
                                    return front_default
                                }
                            } else {
                                return back_shiny
                            }
                        } else {
                            return front_shiny_female
                        }
                    } else {
                        return front_female
                    }
                } else {
                    return back_default
                }
            } else {
                return back_shiny_female
            }
        } else {
            return back_female
        }
    }
}