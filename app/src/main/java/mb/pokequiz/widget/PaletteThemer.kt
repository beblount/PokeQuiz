package mb.pokequiz.widget

import android.app.Activity
import android.graphics.Bitmap
import android.support.v7.graphics.Palette
import mb.pokequiz.utils.ColorsUtils
import mb.pokequiz.utils.mostPopulousSwatch

class PaletteThemer(private val activity: Activity?, private val resource: Bitmap) : Palette.PaletteAsyncListener {

    override fun onGenerated(palette: Palette) {
        if (activity != null) {
            val swatch = palette.mostPopulousSwatch()
            val rgb = swatch.rgb
            val contrast = ColorsUtils.contrast(rgb)

            activity.window.decorView.setBackgroundColor(rgb)
            val isDark = ColorsUtils.isDark(rgb)
            activity.window.statusBarColor = ColorsUtils.scrimify(rgb, isDark, .01f)
        }
    }
}