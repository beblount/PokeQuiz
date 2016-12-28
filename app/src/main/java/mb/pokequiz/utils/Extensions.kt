package mb.pokequiz.utils

import android.os.Build
import android.view.View
import android.widget.Button


/**
 * Created by mbpeele on 12/27/16.
 */
fun View.setLightStatusBar() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        var flags = systemUiVisibility
        flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        systemUiVisibility = flags
    }
}

fun Button.tintDrawable(tint: Int) {
    compoundDrawables
            .filterNotNull()
            .forEach { it.mutate().setTint(tint) }
}