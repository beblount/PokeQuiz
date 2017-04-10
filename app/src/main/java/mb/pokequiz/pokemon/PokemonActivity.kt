package mb.pokequiz.pokemon

import android.content.res.ColorStateList
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.pokemon.*
import mb.pokequiz.R
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.databinding.PokemonBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.utils.ColorsUtils

/**
 * Created by mbpeele on 12/29/16.
 */
class PokemonActivity : PokemonView, MvpActivity<PokemonView, PokemonPresenter>() {

    lateinit var binding : PokemonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.pokemon)
    }

    override fun inject(): PokemonPresenter {
        val component = DaggerPokemonComponent.builder()
                .appComponent(appComponent())
                .pokemonModule(PokemonModule())
                .build()

        return component.presenter()
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
                        name.text = pokemon.name
                        name.setTextColor(contrastColor)
                        binding.root.setBackgroundColor(background)
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        return false
                    }
                })
                .into(binding.image)
    }
}