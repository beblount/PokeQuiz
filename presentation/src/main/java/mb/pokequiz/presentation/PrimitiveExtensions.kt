package mb.pokequiz.utils

/**
 * Created by mbpeele on 4/16/17.
 */
fun Int.length() = when(this) {
    0 -> 1
    else -> Math.log10(Math.abs(toDouble())).toInt() + 1
}