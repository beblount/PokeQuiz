package mb.pokequiz.utils

import android.animation.*
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.Property
import android.view.View
import android.view.Window
import android.view.animation.DecelerateInterpolator
import android.widget.TextView


/**
 * Created by mbpeele on 12/27/16.
 */
object AnimUtils {

    val argbEvaluator : TypeEvaluator<*> = ArgbEvaluator()
    val fastOutSlowIn: FastOutSlowInInterpolator = FastOutSlowInInterpolator()
    val decelerate: DecelerateInterpolator = DecelerateInterpolator()

    fun alpha(view: View, vararg alphas: Float): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, *alphas)
        objectAnimator.interpolator = decelerate
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
        objectAnimator.interpolator = decelerate
        return objectAnimator
    }

    fun statusBarColor(window: Window, statusBarColor: Int) : ObjectAnimator {
        val statusBarColorAnim = ObjectAnimator.ofArgb(window,
                "statusBarColor", window.statusBarColor, statusBarColor)
        statusBarColorAnim.duration = 1000
        statusBarColorAnim.interpolator = fastOutSlowIn
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