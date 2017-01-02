package mb.pokequiz.pokemon

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import mb.pokequiz.R
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.PokemonBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.utils.ColorsUtils

/**
 * Created by mbpeele on 12/29/16.
 */
class PokemonActivity : PokemonView, MvpActivity<PokemonView, PokemonPresenter>() {

    lateinit var binding : PokemonBinding

    companion object {

        const val POKEMON_KEY = "pokemon"

        fun newIntent(context: Context, pokemon: Pokemon) : Intent {
            val intent = Intent(context, PokemonActivity::class.java)
            intent.putExtra(POKEMON_KEY, pokemon)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.pokemon)

        val pokemon = intent.getParcelableExtra<Pokemon>(POKEMON_KEY)
        onPokemonReceived(pokemon)
    }

    override fun inject(): PokemonPresenter {
        val component = DaggerPokemonComponent.builder()
                .appComponent(appComponent())
                .pokemonModule(PokemonModule())
                .build()

        return component.presenter()
    }

    override fun showLoading() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun hideLoading() {
        throw UnsupportedOperationException("not implemented")
    }

    override fun showError() {
        throw UnsupportedOperationException("not implemented")
    }

    fun onPokemonReceived(pokemon: Pokemon) {
        Glide.with(this)
                .load(pokemon.sprites.getFront())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        val palette = Palette.from(resource).generate()
                        val swatch = ColorsUtils.getMostPopulousSwatch(palette)
                        val rgb = swatch.rgb
                        val lightenedColor = ColorsUtils.lighten(rgb, .15)
                        val colorStateList = ColorStateList.valueOf(rgb)
                        val contrastColor = ColorsUtils.contrast(rgb)
                        val background = ColorsUtils.scrimify(rgb,
                                ColorsUtils.isDark(rgb), .1f)

                        window.statusBarColor = rgb
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(binding.image)
    }
}