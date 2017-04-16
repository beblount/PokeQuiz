package mb.pokequiz.pokemon

import android.content.Context
import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import kotlinx.android.synthetic.main.pokemon.*
import mb.pokequiz.R
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.application.PokeApplication
import mb.pokequiz.databinding.PokemonBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.presentation.pokemon.PokemonPresenter
import mb.pokequiz.presentation.pokemon.PokemonView

/**
 * Created by mbpeele on 12/29/16.
 */
class PokemonActivity : PokemonView, MvpActivity<PokemonView, PokemonPresenter>() {

    companion object {
        private const val KEY_POKEMON_ID = "pokemonId"

        fun createIntent(context: Context, pokemonId: Int) : Intent {
            val intent = Intent(context, PokemonActivity::class.java)
            intent.putExtra(KEY_POKEMON_ID, pokemonId)
            return intent
        }
    }

    lateinit var binding : PokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.pokemon)

        tabLayout.setupWithViewPager(pager)

        val id = intent.extras[KEY_POKEMON_ID] as Int
        presenter.getPokemon(id)
    }

    override fun inject(application: PokeApplication): PokemonPresenter {
        val component = DaggerPokemonComponent.builder()
                .appComponent(appComponent())
                .pokemonModule(PokemonModule())
                .build()

        return component.presenter()
    }

    override fun onPokemonReceived(pokemon: Pokemon) {
        val adapter = PokemonFormsPagerAdapter(this, tabLayout, pokemon)
        pager.adapter = adapter
        name.text = presenter.formatPokemonName(pokemon)
    }
}