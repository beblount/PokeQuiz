package mb.pokequiz.presentation.mvp

/**
 * Created by mbpeele on 12/27/16.
 */
interface MvpPresenter<in View : MvpView> {

    fun attach(view: View)

    fun detach()

}