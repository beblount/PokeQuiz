package mb.pokequiz.home

import android.app.AlertDialog
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import mb.pokequiz.R
import mb.pokequiz.base.ui.MvpActivity
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.HomeBinding
import java.util.*

class HomeActivity : HomeView, MvpActivity<HomeView, HomePresenter>() {

    lateinit var binding : HomeBinding
    val random : Random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<HomeBinding>(this, R.layout.home)

        binding.activityHome.setOnClickListener {
            presenter.getPokemon(random.nextInt(721))
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

        Glide.with(this)
                .load(pokemon.sprites.getRandom())
                .asBitmap()
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        Log.e(HomeActivity::class.simpleName, "Glide error: ", e)
                        showError()
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: String?,
                                                 target: Target<Bitmap>?, isFromMemoryCache: Boolean,
                                                 isFirstResource: Boolean): Boolean {
                        binding.image.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)
                        return false
                    }
                })
                .into(binding.image)

        binding.fab.setOnClickListener {
            val text = binding.guess.text.toString()
            if (text.equals(binding.pokemon.name, true)) {
                Toast.makeText(it.context, "YOU DID IT BRO", Toast.LENGTH_SHORT).show()
                presenter.getPokemon(random.nextInt(721))
            } else {
                Toast.makeText(it.context, "YOU DID NOT DO IT BRO", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showLoading() {
        binding.activityHome.setBackgroundColor(ContextCompat.getColor(this, R.color.pokedex_red))
    }

    override fun hideLoading() {
        binding.activityHome.setBackgroundColor(Color.WHITE)
    }

    override fun showError() {
        AlertDialog.Builder(this)
                .setTitle("Shit")
                .setMessage("BRUH")
                .show()
    }
}
