package mb.pokequiz.utils

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.v4.graphics.ColorUtils
import android.support.v7.graphics.Palette



/**
 * Created by mbpeele on 12/27/16.
 */
object ColorsUtils {

    fun isDark(hsl: FloatArray): Boolean {
        return hsl[2] < 0.5f
    }

    fun isDark(@ColorInt color: Int): Boolean {
        val hsl = FloatArray(3)
        android.support.v4.graphics.ColorUtils.colorToHSL(color, hsl)
        return isDark(hsl)
    }

    fun contrast(color: Int): Int {
        val y = (299 * Color.red(color) + 587 * Color.green(color) + 114 * Color.blue(color)) / 1000
        return if (y >= 128) Color.BLACK else Color.WHITE
    }

    fun getMostPopulousSwatch(palette: Palette) : Palette.Swatch {
        var mostPopulous : Palette.Swatch? = null
        for (swatch in palette.swatches) {
            if (mostPopulous == null || swatch.population > mostPopulous.population) {
                mostPopulous = swatch
            }
        }

        return mostPopulous!!
    }

    fun lighten(color: Int, fraction: Double): Int {
        var red = Color.red(color)
        var green = Color.green(color)
        var blue = Color.blue(color)
        red = lightenColor(red, fraction)
        green = lightenColor(green, fraction)
        blue = lightenColor(blue, fraction)
        val alpha = Color.alpha(color)
        return Color.argb(alpha, red, green, blue)
    }

    fun darken(color: Int, fraction: Double): Int {
        var red = Color.red(color)
        var green = Color.green(color)
        var blue = Color.blue(color)
        red = darkenColor(red, fraction)
        green = darkenColor(green, fraction)
        blue = darkenColor(blue, fraction)
        val alpha = Color.alpha(color)

        return Color.argb(alpha, red, green, blue)
    }

    private fun darkenColor(color: Int, fraction: Double): Int {
        return Math.max(color - color * fraction, 0.0).toInt()
    }

    private fun lightenColor(color: Int, fraction: Double): Int {
        return Math.min(color + color * fraction, 255.0).toInt()
    }

    @ColorInt fun scrimify(@ColorInt color: Int,
                           isDark: Boolean,
                           @FloatRange(from = 0.0, to = 1.0) multplier: Float): Int {
        var lightnessMultiplier = multplier
        val hsl = FloatArray(3)
        ColorUtils.colorToHSL(color, hsl)

        if (!isDark) {
            lightnessMultiplier += 1f
        } else {
            lightnessMultiplier = 1f - lightnessMultiplier
        }

        hsl[2] = MathUtils.constrain(0f, 1f, hsl[2] * lightnessMultiplier)
        return android.support.v4.graphics.ColorUtils.HSLToColor(hsl)
    }
}