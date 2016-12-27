package mb.pokequiz.base.mvp

/**
 * Created by mbpeele on 12/27/16.
 */
interface MvpLoadingView : MvpView {

    fun showLoading()

    fun hideLoading()

    fun showError()

}