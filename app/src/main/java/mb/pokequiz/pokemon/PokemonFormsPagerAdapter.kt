package mb.pokequiz.pokemon

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import mb.pokequiz.R
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.databinding.PokemonImageBinding

/**
 * Created by mbpeele on 4/14/17.
 */
class PokemonFormsPagerAdapter(val context: Context, val pokemon: Pokemon) : PagerAdapter() {

    val data = pokemon.sprites.nonNullImages()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = LayoutInflater.from(context)
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

    private fun load(url: String, binding: PokemonImageBinding) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(binding.image)
    }
}