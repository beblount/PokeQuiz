package mb.pokequiz.utils

import android.graphics.Bitmap
import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange
import android.support.annotation.IntDef
import android.support.v4.graphics.ColorUtils
import android.support.v7.graphics.Palette


/**
 * Created by mbpeele on 12/27/16.
 */
object ColorsUtils {

    const val IS_LIGHT = 0L
    const val IS_DARK = 1L
    const val LIGHTNESS_UNKNOWN = 2L

    @Retention()
    @IntDef(IS_LIGHT, IS_DARK, LIGHTNESS_UNKNOWN)
    annotation class Lightness

    fun alpha(color: Int, @FloatRange(from = 0.0, to = 1.0) multiplier: Float) : Int {
        val alphaMultiplier = multiplier * 255
        return Color.argb(alphaMultiplier.toInt(), Color.red(color), Color.green(color), Color.blue(color))
    }

    @Lightness fun isDark(palette: Palette): Long {
        val mostPopulous = palette.mostPopulousSwatch()
        return if (isDark(mostPopulous.hsl)) IS_DARK else IS_LIGHT
    }

    fun isDark(bitmap: Bitmap, backupPixelX: Int, backupPixelY: Int): Boolean {
        // first try palette with a small color quant size
        val palette = Palette.from(bitmap).maximumColorCount(3).generate()
        if (palette.swatches.size > 0) {
            return isDark(palette) == IS_DARK
        } else {
            // if palette failed, then check the color of the specified pixel
            return isDark(bitmap.getPixel(backupPixelX, backupPixelY))
        }
    }

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