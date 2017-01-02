package mb.pokequiz.utils

import android.content.Context
import android.graphics.Typeface
import android.support.annotation.StringRes
import java.io.IOException
import java.util.*

/**
 * Created by mbpeele on 12/18/16.
 */

object FontUtils {

    private var fontMap: HashMap<String, Typeface>? = null

    private fun init(context: Context) {
        fontMap = HashMap<String, Typeface>()
        val assetManager = context.assets
        try {
            val fontFileNames = assetManager.list("fonts")
            for (fontFileName in fontFileNames) {
                val typeface = Typeface.createFromAsset(assetManager, "fonts/" + fontFileName)
                fontMap!!.put(fontFileName, typeface)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    operator fun get(context: Context, @StringRes stringRes: Int): Typeface {
        return get(context, context.getString(stringRes))
    }

    operator fun get(context: Context, font: String): Typeface {
        if (fontMap == null) {
            init(context)
        }
        val typeface = fontMap!![font] ?: throw IllegalFontException(font)
        return typeface
    }

    private class IllegalFontException
        internal constructor(fontName: String) :
            IllegalArgumentException("Supplied font name $fontName must match file name in assets/fonts/ directory")

}
