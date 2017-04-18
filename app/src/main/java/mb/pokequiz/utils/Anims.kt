package mb.pokequiz.utils

import android.animation.*
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.Property
import android.view.View
import android.view.Window
import android.view.animation.BounceInterpolator
import android.view.animation.DecelerateInterpolator
import android.view.animation.OvershootInterpolator
import android.widget.TextView


/**
 * Created by mbpeele on 12/27/16.
 */
object AnimUtils {

    val STATUS_BAR = "statusBarColor"

    val OVERSHOOT : OvershootInterpolator = OvershootInterpolator()
    val BOUNCE: BounceInterpolator = BounceInterpolator()
    val FAST_OUT_SLOW_IN: FastOutSlowInInterpolator = FastOutSlowInInterpolator()
    val DECELERATE: DecelerateInterpolator = DecelerateInterpolator()

    fun alpha(view: View, vararg alphas: Float): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, *alphas)
        objectAnimator.interpolator = DECELERATE
        return objectAnimator
    }

    fun visible(view: View): ObjectAnimator {
        val alpha = alpha(view, 0f, 1f)
        alpha.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                view.visibility = View.VISIBLE
            }
        })
        return alpha
    }

    fun gone(view: View): ObjectAnimator {
        val alpha = alpha(view, 1f, 0f)
        alpha.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view.visibility = View.GONE
            }
        })
        return alpha
    }

    fun invisible(view: View): ObjectAnimator {
        val alpha = alpha(view, 1f, 0f)
        alpha.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                view.visibility = View.INVISIBLE
            }
        })
        return alpha
    }

    fun scale(view: View, vararg scales: Float) : ObjectAnimator {
        val animator = ObjectAnimator.ofPropertyValuesHolder(
                view,
                PropertyValuesHolder.ofFloat(View.SCALE_X, *scales),
                PropertyValuesHolder.ofFloat(View.SCALE_Y, *scales))
        animator.duration = 500
        animator.interpolator = DECELERATE
        return animator
    }

    fun textScale(textView: TextView, vararg scales: Float): ObjectAnimator {
        val floatProperty = object : FloatProperty<TextView>("") {
            override fun setValue(view: TextView, value: Float) {
                view.textScaleX = value
            }

            override operator fun get(view: TextView): Float? {
                return view.textScaleX
            }
        }

        val objectAnimator = ObjectAnimator.ofFloat(textView, floatProperty, *scales)
        objectAnimator.duration = 500
        objectAnimator.interpolator = DECELERATE
        return objectAnimator
    }

    fun statusBarColor(window: Window, statusBarColor: Int) : ObjectAnimator {
        val statusBarColorAnim = ObjectAnimator.ofArgb(window,
                "statusBarColor", window.statusBarColor, statusBarColor)
        statusBarColorAnim.duration = 1000
        statusBarColorAnim.interpolator = FAST_OUT_SLOW_IN
        return statusBarColorAnim
    }

    fun colorStateList(view: View, vararg colorStateLists : ColorStateList) : ValueAnimator {
        val animator = argb(*colorStateLists)
        animator.duration = 500
        animator.addUpdateListener {
            view.backgroundTintList = ColorStateList.valueOf(it.animatedValue as Int)
        }
        return animator
    }

    fun colorFilter(drawable: Drawable, vararg colors: Int) : ValueAnimator {
        drawable.mutate()
        val animator = argb(*colors)
        animator.duration = 500
        animator.addUpdateListener {
            drawable.setColorFilter(it.animatedValue as Int, PorterDuff.Mode.SRC_IN)
        }
        return animator
    }

    fun argb(vararg colors : Int) : ValueAnimator {
        return ValueAnimator.ofArgb(*colors)
    }

    fun argb(vararg colorStateLists : ColorStateList) : ValueAnimator {
        val array = colorStateLists
                .map { it.defaultColor }
                .toIntArray()
        return argb(*array)
    }

    abstract class FloatProperty<T>(name: String) : Property<T, Float>(Float::class.java, name) {

        abstract fun setValue(view: T, value: Float)

        override operator fun set(view: T, value: Float) {
            setValue(view, value)
        }
    }

    abstract class IntProperty<T>(name: String) : Property<T, Int>(Int::class.java, name) {

        abstract fun setValue(view: T, value: Int)

        override operator fun set(view: T, value: Int) {
            setValue(view, value)
        }
    }
}