package mb.pokequiz.ui

import android.databinding.BindingAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import mb.pokequiz.utils.FontUtils

/**
 * Created by mbpeele on 12/23/16.
 */

@BindingAdapter("font")
fun setFont(view: TextView, fontName: String) {
    val font = FontUtils[view.context, fontName]
    view.typeface = font
}

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, url: String?) {
    if (url == null) {
        return
    }

    Glide.with(view.context).load(url).into(view)
}
