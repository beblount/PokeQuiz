package mb.pokequiz.quiz

import android.animation.AnimatorSet
import android.app.ActivityOptions
import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.res.ColorStateList
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.graphics.Palette
import android.text.Spannable
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.quiz.*
import mb.pokequiz.R
import mb.pokequiz.data.model.Hint
import mb.pokequiz.data.model.Pokemon
import mb.pokequiz.databinding.QuizBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.pokemon.PokemonActivity
import mb.pokequiz.utils.AnimUtils
import mb.pokequiz.utils.ColorsUtils
import mb.pokequiz.utils.Logger
import mb.pokequiz.utils.startAnim
import java.util.*


class QuizActivity : QuizView, MvpActivity<QuizView, QuizPresenter>(), Timer.TimerListener {

    lateinit var binding : QuizBinding
    var progressDialog : ProgressDialog ?= null
    var pokemon : Pokemon ?= null
    val hints : ArrayList<Hint> = ArrayList()

    var rgb : Int = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<QuizBinding>(this, R.layout.quiz)

        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        failure.movementMethod = LinkMovementMethod.getInstance()

        presenter.getNextPokemon()

        hint.setOnClickListener {
            if (pokemon != null) {
                val textColor = Color.WHITE
                var length = Snackbar.LENGTH_SHORT
                var snackbarText = ""

                if (hints.size == 2) {
                    snackbarText = "No more hints"
                } else {
                    length = Snackbar.LENGTH_INDEFINITE

                    val hint : Hint ?
                    val types = hints.map(Hint::hintType)

                    if (types.contains(Hint.ABILITY)) {
                        val text = pokemon!!.types.map { it.type.name }
                        snackbarText = getString(R.string.hint_formatter, "types", text)
                        hint = Hint(snackbarText, Hint.TYPE)

                        hints.add(hint)
                    } else {
                        val text = pokemon!!.abilities.map { it.ability.name }
                        snackbarText = getString(R.string.hint_formatter, "abilities", text)
                        hint = Hint(snackbarText, Hint.ABILITY)

                        hints.add(hint)
                    }
                }

                val snackbar = Snackbar.make(binding.root, snackbarText, length)
                        .setActionTextColor(textColor)
                        .setAction("Dismiss") { }

                val textView = snackbar.view.findViewById(android.support.design.R.id.snackbar_text) as TextView
                textView.setTextColor(textColor)

                snackbar.show()
            }
        }
    }

    override fun inject(): QuizPresenter {
        val component = DaggerQuizComponent.builder()
                .appComponent(appComponent())
                .quizModule(QuizModule())
                .build()

        return component.presenter()
    }

    override fun onPokemonReceived(pokemon: Pokemon) {
        this.pokemon = pokemon

        guess.isEnabled = true

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View?) {
                // go to pokemon activity with transition
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = true
            }
        }
        val spannable = failure.text as Spannable
        spannable.setSpan(clickableSpan, 0, spannable.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        failure.text = spannable

        Glide.with(this)
                .load(pokemon.sprites.getFront())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onResourceReady(resource: Bitmap, model: String?, target: Target<Bitmap>?,
                                                 isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        image.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)

                        Palette.from(resource)
                                .generate {
                                    val swatch = ColorsUtils.getMostPopulousSwatch(it)
                                    rgb = swatch.rgb
                                    val colorStateList = ColorStateList.valueOf(rgb)
                                    val contrastColor = ColorsUtils.contrast(colorStateList.defaultColor)

                                    val guessBackground = AnimUtils.colorStateList(guess,
                                            guess.backgroundTintList ?: ColorStateList.valueOf(R.color.primary_text),
                                            colorStateList)

                                    val fabBackground = AnimUtils.colorStateList(fab,
                                            fab.backgroundTintList ?: ColorStateList.valueOf(R.color.accent),
                                            colorStateList)
                                    val fabDrawable = AnimUtils.colorFilter(fab.drawable,
                                            fab.imageTintList?.defaultColor ?: R.color.icons,
                                            contrastColor)

                                    val statusBar = AnimUtils.statusBarColor(window, rgb)
                                    val backIcon = AnimUtils.colorFilter(toolbar.navigationIcon!!,
                                            R.color.icons, rgb)

                                    val textScale = AnimUtils.textScale(failure, .8f, 1f)
                                    val alpha = AnimUtils.alpha(failure, 0f, 1f)
                                    val visible = AnimUtils.visible(failure)

                                    val set = AnimatorSet()
                                    set.playTogether(guessBackground, fabBackground, fabDrawable,
                                            statusBar, backIcon, textScale, alpha, visible)
                                    set.start()
                                }

                        hideLoading()
                        timer.start(60, this@QuizActivity)
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?,
                                             isFirstResource: Boolean): Boolean {
                        Logger.error(QuizActivity::class.simpleName!!, "Glide error", e)
                        showError()
                        return false
                    }
                })
                .into(image)

        fab.setOnClickListener {
            val text = guess.text.toString()
            if (text.equals(pokemon.name, true)) {
                image.clearColorFilter()
                guess.isEnabled = false
                timer.stop()

                goToPokemonScreen()
            } else {
                fab.drawable.startAnim()
            }
        }
    }

    override fun showLoading() {
        Glide.clear(image)
        guess.setText("")
        timer.stop()

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

    override fun onTimeout() {
        // run some shitty animation then go to Pokemon screen
        // Just do some random stuff for now
        guess.isEnabled = false
        fab.isEnabled = false
        hint.isEnabled = false
        grade.isEnabled = false
        failure.isEnabled = false
        image.clearColorFilter()

        timer.animate()
                .scaleX(1.2f)
                .scaleY(1.2f)
                .setInterpolator(AnimUtils.decelerate)
                .setDuration(700)
                .start()
    }

    private fun goToPokemonScreen() {
        val intent = PokemonActivity.newIntent(this@QuizActivity, pokemon!!)
        val options = ActivityOptions.makeSceneTransitionAnimation(this,
                image, getString(R.string.to_pokemon_image))
        startActivity(intent, options.toBundle())
    }
}
