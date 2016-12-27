package mb.pokequiz.home

import android.databinding.DataBindingUtil
import android.os.Bundle
import mb.pokequiz.R
import mb.pokequiz.base.ui.MvpActivity
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.HomeBinding

class HomeActivity : HomeView, MvpActivity<HomeView, HomePresenter>() {

    lateinit var binding : HomeBinding

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
}
