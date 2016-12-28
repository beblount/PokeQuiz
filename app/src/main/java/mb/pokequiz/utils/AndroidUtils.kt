package mb.pokequiz.utils

import android.os.Build

/**
 * Created by mbpeele on 12/27/16.
 */
object AndroidUtils {

    fun isMarshmallow() : Boolean {
        return Build.VERSION.SDK_INT > Build.VERSION_CODES.M
    }
}