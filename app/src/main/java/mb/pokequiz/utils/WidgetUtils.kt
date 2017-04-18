package mb.pokequiz.utils

import android.app.Activity
import android.graphics.drawable.Animatable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Build
import android.support.v7.graphics.Palette
import android.view.View
import android.widget.Button
import mb.pokequiz.R


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

fun View.isVisible() : Boolean {
    return visibility == View.VISIBLE
}

fun Button.tintDrawables(tint: Int) {
    compoundDrawables
            .filterNotNull()
            .forEach { it.mutate().setTint(tint) }
}

fun Drawable.startAnim() {
    (this as Animatable).start()
}

fun Activity.background() : Int {
    val background = window.decorView.background
    if (background is ColorDrawable) {
        return background.color
    } else {
        val array = theme.obtainStyledAttributes(intArrayOf(android.R.attr.colorBackground))
        val backgroundColor = array.getColor(0, R.color.primary_dark)
        array.recycle()
        return backgroundColor
    }
}

fun Palette.mostPopulousSwatch() : Palette.Swatch {
    var mostPopulous : Palette.Swatch? = null
    for (swatch in swatches) {
        if (mostPopulous == null || swatch.population > mostPopulous.population) {
            mostPopulous = swatch
        }
    }

    return mostPopulous!!
}

object UIUtils {

    fun setLightStatusBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
        }
    }

}
