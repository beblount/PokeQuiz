package mb.pokequiz.utils

import android.util.Log

/**
 * Created by mbpeele on 12/29/16.
 */
object Logger {

    fun error(tag: String, message: String) {
        error(tag, message, null)
    }

    fun error(tag: String, message: String, throwable: Throwable?) {
        Log.e(tag, message, throwable)
    }
}