package mb.pokequiz.pokemon

import android.animation.AnimatorSet
import android.app.Activity
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.support.design.widget.TabLayout
import android.support.v4.view.PagerAdapter
import android.support.v7.graphics.Palette
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import mb.pokequiz.R
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.databinding.PokemonImageBinding
import mb.pokequiz.utils.AnimUtils
import mb.pokequiz.utils.ColorsUtils
import java.lang.Exception

/**
 * Created by mbpeele on 4/14/17.
 */
class PokemonFormsPagerAdapter(val activity: Activity, val tabLayout: TabLayout, val pokemon: Pokemon) : PagerAdapter() {

    val data = pokemon.sprites.nonNullImages()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(activity)
        val binding = DataBindingUtil.inflate<PokemonImageBinding>(inflater, R.layout.pokemon_image, container, false)
        container.addView(binding.root)

        load(data[position], binding)

        return binding.root
    }

    override fun destroyItem(collection: ViewGroup, position: Int, view: Any) {
        collection.removeView(view as View)
    }

    override fun isViewFromObject(view: View?, `object`: Any?): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return pokemon.sprites.nonNullCount()
    }

    override fun getPageTitle(position: Int): CharSequence {
        return ""
    }

    private fun load(url: String, binding: PokemonImageBinding) {
        Glide.with(activity)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?, isFirstResource: Boolean): Boolean {
                        Log.e(PokemonFormsPagerAdapter::class.simpleName!!, "Glide error", e)
                        return false
                    }

                    override fun onResourceReady(resource: Bitmap?, model: String?, target: Target<Bitmap>?, isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        Palette.from(resource)
                                .generate {
                                    val swatch = ColorsUtils.getMostPopulousSwatch(it)
                                    val rgb = swatch.rgb
                                    val statusBar = AnimUtils.statusBarColor(activity.window, rgb)

                                    tabLayout.setSelectedTabIndicatorColor(rgb)

                                    val set = AnimatorSet()
                                    set.playTogether(statusBar)
                                    set.start()
                                }

                        return false
                    }
                })
                .into(binding.image)
    }
}