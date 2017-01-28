package mb.pokequiz.mvp

import java.lang.ref.WeakReference

/**
 * Created by mbpeele on 12/27/16.
 */
open class BasePresenter<View : MvpView> : MvpPresenter<View> {

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

    protected fun get() : View? {
        return reference?.get()
    }
}