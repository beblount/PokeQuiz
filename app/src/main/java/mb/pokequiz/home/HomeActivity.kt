package mb.pokequiz.home

import android.app.AlertDialog
import android.app.ProgressDialog
import android.databinding.DataBindingUtil
import android.os.Bundle
import mb.pokequiz.R
import mb.pokequiz.base.ui.MvpActivity
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.HomeBinding

class HomeActivity : HomeView, MvpActivity<HomeView, HomePresenter>() {

    lateinit var binding : HomeBinding
    var progressDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<HomeBinding>(this, R.layout.home)

        binding.activityHome.setOnClickListener {
            presenter.getRandomPokemon()
        }
    }

    override fun inject(): HomePresenter {
        val component = DaggerHomeComponent.builder()
                .appComponent(appComponent())
                .build()

        return component.presenter()
    }

    override fun onPokemonReceive(pokemon: Pokemon) {
        binding.pokemon = pokemon
    }

    override fun showLoading() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", "POKEMON!!!!")
        } else {
            progressDialog!!.show()
        }
    }

    override fun hideLoading() {
        progressDialog!!.hide()
    }

    override fun showError() {
        AlertDialog.Builder(this)
                .setTitle("Shit")
                .setMessage("BRUH")
                .show()
    }
}
