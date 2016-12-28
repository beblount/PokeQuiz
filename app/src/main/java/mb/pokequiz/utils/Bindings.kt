package mb.pokequiz.utils

import android.databinding.BindingAdapter
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

/**
 * Created by mbpeele on 12/23/16.
 */

@BindingAdapter("font")
fun setFont(view: View, fontName: String) {
    val font = FontUtils[view.context, fontName]
    if (view is TextView) {
        view.typeface = font
    } else if (view is Toolbar) {
        for (i in 0..view.childCount) {
            val child = view.getChildAt(i)
            if (child is TextView) {
                child.typeface = font
            }
        }
    }
}

@BindingAdapter("imageUrl")
fun setImage(view: ImageView, url: String?) {
    Glide.with(view.context).load(url).into(view)
}


