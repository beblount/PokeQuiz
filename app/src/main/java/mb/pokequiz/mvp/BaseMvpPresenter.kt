package mb.pokequiz.mvp

import java.lang.ref.WeakReference

/**
 * Created by mbpeele on 12/27/16.
 */
open class BaseMvpPresenter<View : MvpView> : MvpPresenter<View> {

    private var reference : WeakReference<View>? = null

    override fun attach(view: View) {
        if (reference == null) {
            reference = WeakReference(view)
        }
    }

    override fun detach() {
        if (reference != null) {
            reference!!.clear()
            reference = null
        }
    }

    protected fun get() : View {
        return reference!!.get()
    }

    protected fun attached() : Boolean {
        if (reference == null) {
            return false
        } else {
            val view = reference?.get()
            return if (view == null) false else true
        }
    }
}