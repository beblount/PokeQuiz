package mb.pokequiz.home

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.res.ColorStateList
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.graphics.Palette
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import mb.pokequiz.BuildConfig
import mb.pokequiz.R
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.HomeBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.utils.ColorsUtils
import mb.pokequiz.utils.tintDrawable
import java.util.*
import java.util.concurrent.TimeUnit


class HomeActivity : HomeView, MvpActivity<HomeView, HomePresenter>() {

    lateinit var binding : HomeBinding
    val random : Random = Random()
    var progressDialog : ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<HomeBinding>(this, R.layout.home)

        setSupportActionBar(binding.toolbar)

        val color = ContextCompat.getColor(this, R.color.primary_dark)
        binding.gradeButton.tintDrawable(color)
        binding.hintButton.tintDrawable(color)
        binding.timerButton.drawable?.setTint(color)

        presenter.getPokemon(random.nextInt(721))
    }

    override fun inject(): HomePresenter {
        val component = DaggerHomeComponent.builder()
                .appComponent(appComponent())
                .build()

        return component.presenter()
    }

    override fun onPokemonReceive(pokemon: Pokemon) {
        binding.guess.isEnabled = true

        Glide.with(this)
                .load(pokemon.sprites.getRandom())
                .asBitmap()
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onResourceReady(resource: Bitmap, model: String?, target: Target<Bitmap>?,
                                                 isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
//                        binding.image.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)

                        Palette.from(resource)
                                .generate {
                                    val vibrantSwitch = ColorsUtils.getMostPopulousSwatch(it)
                                    val rgb = vibrantSwitch.rgb
                                    val lightenedColor = ColorsUtils.lighten(rgb, .15)
                                    val colorStateList = ColorStateList.valueOf(rgb)
                                    val contrastColor = ColorsUtils.contrast(rgb)
                                    val background = ColorsUtils.scrimify(rgb,
                                            ColorsUtils.isDark(rgb), .1f)

                                    binding.guess.backgroundTintList = colorStateList
                                    binding.guess.setTextColor(contrastColor)
                                    binding.fab.backgroundTintList = colorStateList
                                    binding.fab.drawable.mutate().setTint(contrastColor)
                                    binding.toolbar.setBackgroundColor(lightenedColor)
                                    binding.toolbar.setTitleTextColor(ColorsUtils.contrast(lightenedColor))
                                    window.statusBarColor = rgb
                                    binding.root.setBackgroundColor(background)
                                }
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?,
                                             isFirstResource: Boolean): Boolean {
                        Log.e(HomeActivity::class.simpleName, "Glide error: ", e)
                        showError()
                        return false
                    }
                })
                .into(binding.image)

        binding.fab.setOnClickListener {
            val text = binding.guess.text.toString()
            if (text.equals(pokemon.name, true) || BuildConfig.DEBUG) {
                Toast.makeText(it.context, "YOU DID IT BRO", Toast.LENGTH_SHORT).show()

                binding.image.clearColorFilter()
                binding.guess.isEnabled = false
                binding.name.text = pokemon.name

                Observable.timer(5, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                        .subscribe {
                            presenter.getPokemon(random.nextInt(721))
                        }
            } else {
                Toast.makeText(it.context, "YOU DID NOT DO IT BRO", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun showLoading() {
        Glide.clear(binding.image)
        binding.name.setText("")
        binding.guess.setText("")

        if (progressDialog == null) {
            progressDialog = ProgressDialog.show(this, "", "POKEMON!!!!")
        }

        progressDialog!!.show()
    }

    override fun hideLoading() {
        progressDialog?.hide()
    }

    override fun showError() {
        AlertDialog.Builder(this)
                .setTitle("Shit")
                .setMessage("BRUH")
                .show()
    }
}
