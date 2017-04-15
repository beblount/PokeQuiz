package mb.pokequiz.quiz

import android.animation.AnimatorSet
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
import android.util.Log
import android.view.View
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.quiz.*
import mb.pokequiz.R
import mb.pokequiz.api.model.Hint
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.application.PokeApplication
import mb.pokequiz.databinding.QuizBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.pokemon.PokemonActivity
import mb.pokequiz.utils.AnimUtils
import mb.pokequiz.utils.ColorsUtils
import mb.pokequiz.utils.startAnim
import java.util.*


class QuizActivity : QuizView, MvpActivity<QuizView, QuizPresenter>(), Timer.TimerListener {

    val hints : ArrayList<Hint> = ArrayList()
    var pokemon: Pokemon ?= null

    var rgb : Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<QuizBinding>(this, R.layout.quiz)

        presenter.getNextPokemon()

        toolbar.title = ""
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        failure.movementMethod = LinkMovementMethod.getInstance()

        hint.setOnClickListener {
            if (pokemon != null) {
                val pokemon = pokemon!!

                val textColor = Color.WHITE
                var length = Snackbar.LENGTH_SHORT
                var snackbarText = ""

                if (hints.size == 2) {
                    snackbarText = "No more hints"
                } else {
                    length = Snackbar.LENGTH_INDEFINITE

                    val hint : Hint ?
                    val types = hints.map(Hint::hintType)

                    if (types.contains(Hint.HintType.ABILITY)) {
                        val text = pokemon.types.map { it.type.name }
                        snackbarText = getString(R.string.hint_formatter, "types", text)
                        hint = Hint(snackbarText, Hint.HintType.TYPE)

                        hints.add(hint)
                    } else {
                        val text = pokemon.abilities.map { it.ability.name }
                        snackbarText = getString(R.string.hint_formatter, "abilities", text)
                        hint = Hint(snackbarText, Hint.HintType.ABILITY)

                        hints.add(hint)
                    }
                }

                val snackbar = Snackbar.make(root, snackbarText, length)
                        .setActionTextColor(textColor)
                        .setAction("Dismiss") { }

                val textView = snackbar.view.findViewById(android.support.design.R.id.snackbar_text) as TextView
                textView.setTextColor(textColor)

                snackbar.show()
            }
        }
    }

    override fun inject(application: PokeApplication): QuizPresenter {
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

                                    val alpha = AnimUtils.alpha(failure, 0f, 1f)
                                    val visible = AnimUtils.visible(failure)

                                    val set = AnimatorSet()
                                    set.playTogether(guessBackground, fabBackground, fabDrawable,
                                            statusBar, backIcon, alpha, visible)
                                    set.start()
                                }

                        timer.start(5, this@QuizActivity)

                        failure.setOnClickListener {
                            goToPokemonScreen(pokemon.id)
                        }
                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?,
                                             isFirstResource: Boolean): Boolean {
                        Log.e(QuizActivity::class.simpleName!!, "Glide error", e)
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

                goToPokemonScreen(pokemon.id)
            } else {
                fab.drawable.startAnim()
            }
        }
    }

    override fun showError() {
        image.setImageDrawable(getDrawable(R.drawable.ic_error_outline_black_24dp))
        image.setOnClickListener {
            presenter.getNextPokemon()
        }
    }

    override fun showLoading() {
        image.setImageDrawable(null)
        image.setOnClickListener(null)
        image.clearColorFilter()

        guess.isEnabled = false
        fab.isEnabled = false
        hint.isEnabled = false
        grade.isEnabled = false
        failure.isEnabled = false
        failure.setOnClickListener(null)

        val alpha = AnimUtils.alpha(failure, 0f, 1f)
        val visible = AnimUtils.invisible(failure)
        val set = AnimatorSet()
        set.playTogether(alpha, visible)
        set.start()
    }

    override fun onTimeout() {
        // run some animation then go to Pokemon screen
        // Just do some random stuff for now
        goToPokemonScreen(pokemon!!.id)
//        guess.isEnabled = false
//        fab.isEnabled = false
//        hint.isEnabled = false
//        grade.isEnabled = false
//        failure.isEnabled = false
//        image.clearColorFilter()
//
//        timer.animate()
//                .scaleX(1.2f)
//                .scaleY(1.2f)
//                .setInterpolator(AnimUtils.decelerate)
//                .setDuration(2000)
//                .setListener(object : AnimatorListenerAdapter() {
//                    override fun onAnimationEnd(animation: Animator?) {
//                        goToPokemonScreen()
//                    }
//                })
//                .start()
    }

    private fun goToPokemonScreen(pokemonId: Int) {
        val intent = PokemonActivity.createIntent(this, pokemonId)
        startActivity(intent)
    }
}
