package mb.pokequiz.widget

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.util.AttributeSet
import android.view.View

abstract class BaseBehavior<T : View>(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<T>()