package mb.pokequiz.utils

import android.animation.ObjectAnimator
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.view.Window


/**
 * Created by mbpeele on 12/27/16.
 */
object AnimUtils {

    val fastOutSlowIn: FastOutSlowInInterpolator = FastOutSlowInInterpolator()

    fun statusBarColor(window: Window, statusBarColor: Int) : ObjectAnimator {
        val statusBarColorAnim = ObjectAnimator.ofArgb(window,
                "statusBarColor", window.statusBarColor, statusBarColor)
        statusBarColorAnim.duration = 1000
        statusBarColorAnim.interpolator = fastOutSlowIn
        return statusBarColorAnim
    }
}