package mb.pokequiz.utils

/**
 * Created by mbpeele on 12/27/16.
 */
object MathUtils {

    fun constrain(min: Float, max: Float, v: Float): Float {
        return Math.max(min, Math.min(max, v))
    }
}