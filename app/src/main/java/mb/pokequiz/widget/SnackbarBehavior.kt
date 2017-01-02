package mb.pokequiz.widget

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.util.AttributeSet
import android.view.View



class SnackbarBehavior(context: Context, attrs: AttributeSet) : BaseBehavior<View>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout?, child: View?, dependency: View?): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val translationY = Math.max(0f, dependency.height - dependency.translationY)
        child.translationY = -translationY
        return true
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        super.onDependentViewRemoved(parent, child, dependency)
        val translationY = Math.min(0, parent.bottom - child.bottom).toFloat()
        child.animate()
                .translationY(translationY)
    }
}