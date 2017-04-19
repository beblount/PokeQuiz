package mb.pokequiz.utils

import android.view.View
import android.view.ViewTreeObserver

import java.lang.ref.SoftReference

object PreDrawer {

    fun <T : View> addPreDrawer(view: T, function: (View) -> Boolean) {
        val softReference = SoftReference(view)

        val viewTreeObserver = view.viewTreeObserver
        viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                if (viewTreeObserver.isAlive) {
                    viewTreeObserver.removeOnPreDrawListener(this)
                } else {
                    view.viewTreeObserver.removeOnPreDrawListener(this)
                }

                val reference = softReference.get()
                if (reference != null) {
                    return function.invoke(reference)
                }

                return false
            }
        })
    }
}