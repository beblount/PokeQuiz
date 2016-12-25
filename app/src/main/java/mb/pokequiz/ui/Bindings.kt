package mb.pokequiz.ui

import android.databinding.BindingAdapter
import android.widget.TextView
import mb.pokequiz.utils.FontUtils

/**
 * Created by mbpeele on 12/23/16.
 */

@BindingAdapter("font")
fun setFont(view: TextView, fontName: String) {
    val font = FontUtils[view.context, fontName]
    view.typeface = font
}
