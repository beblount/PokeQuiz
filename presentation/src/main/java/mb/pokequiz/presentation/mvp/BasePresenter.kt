package mb.pokequiz.presentation.mvp

import java.lang.ref.WeakReference

open class BasePresenter<View : MvpView>(val schedulerProvider: SchedulerProvider) : MvpPresenter<View> {

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