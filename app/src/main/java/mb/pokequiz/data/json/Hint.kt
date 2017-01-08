package mb.pokequiz.data.json

import android.support.annotation.IntDef

/**
 * Created by mbpeele on 1/1/17.
 */
data class Hint(val text: String, @HintType val hintType: Long) {

    companion object {

        const val ABILITY = 0L
        const val TYPE = 1L

        @Retention(AnnotationRetention.SOURCE)
        @IntDef(ABILITY, TYPE)
        annotation class HintType
    }
}