package mb.pokequiz.quiz

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.databinding.DataBindingUtil
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import kotlinx.android.synthetic.main.quiz.*
import mb.pokequiz.BuildConfig
import mb.pokequiz.R
import mb.pokequiz.api.model.Pokemon
import mb.pokequiz.application.PokeApplication
import mb.pokequiz.databinding.QuizBinding
import mb.pokequiz.mvp.MvpActivity
import mb.pokequiz.pokemon.PokemonActivity
import mb.pokequiz.presentation.quiz.QuizPresenter
import mb.pokequiz.presentation.quiz.QuizView
import mb.pokequiz.utils.Anims
import mb.pokequiz.utils.TextWatcherAdapter


class QuizActivity : QuizView, MvpActivity<QuizView, QuizPresenter>(), Timer.TimerListener {

    var pokemon: Pokemon ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataBindingUtil.setContentView<QuizBinding>(this, R.layout.quiz)

        presenter.getNextPokemon()

        failure.movementMethod = LinkMovementMethod.getInstance()
    }

    override fun inject(application: PokeApplication): QuizPresenter {
        val component = DaggerQuizComponent.builder()
                .appComponent(appComponent())
                .quizModule(QuizModule())
                .build()

        return component.presenter()
    }

    override fun onPokemonReceived(pokemon: Pokemon) {
        val set = AnimatorSet()
        set.playTogether(Anims.visible(content), Anims.visible(quizControls), Anims.invisible(loading), Anims.invisible(error))
        set.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                loading.stop()
            }
        })
        set.duration = 150
        set.start()

        if (BuildConfig.DEBUG) {
            Toast.makeText(this, pokemon.name, Toast.LENGTH_LONG).show()
        }

        this.pokemon = pokemon

        Glide.with(this)
                .load(pokemon.sprites.getFront())
                .asBitmap()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(object : RequestListener<String, Bitmap> {
                    override fun onResourceReady(resource: Bitmap, model: String?, target: Target<Bitmap>?,
                                                 isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                        image.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP)

                        timer.start(5, this@QuizActivity)

                        guess.isEnabled = true
                        hint.isEnabled = true
                        grade.isEnabled = true
                        failure.isEnabled = true

                        val clickableSpan = object : ClickableSpan() {
                            override fun onClick(widget: View?) {
                                goToPokemonScreen()
                            }

                            override fun updateDrawState(ds: TextPaint) {
                                ds.isUnderlineText = true
                            }
                        }

                        val spannable = failure.text as Spannable
                        spannable.setSpan(clickableSpan, 0, spannable.length, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
                        failure.text = spannable

                        val set = AnimatorSet()
                        set.playTogether(Anims.visible(failure), Anims.visible(image))
                        set.start()

                        return false
                    }

                    override fun onException(e: Exception?, model: String?, target: Target<Bitmap>?,
                                             isFirstResource: Boolean): Boolean {
                        Log.e(QuizActivity::class.simpleName!!, "Glide error", e)
                        return false
                    }
                })
                .into(image)

        guess.removeTextChangedListener(textChangedListener)
        guess.addTextChangedListener(textChangedListener)
    }

    override fun showError() {
        error.set(R.drawable.sad_pikachu, R.string.error_couldnt_get_pokemon)

        val set = AnimatorSet()
        set.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                error.setOnClickListener({
                    Anims.visible(content).start()
                    Anims.invisible(error).start()

                    presenter.retry()
                })
            }
        })
        set.playTogether(Anims.visible(error), Anims.invisible(content), Anims.invisible(loading))
        set.start()
    }

    override fun showLoading() {
        guess.isEnabled = false
        hint.isEnabled = false
        grade.isEnabled = false
        failure.isEnabled = false
        failure.setOnClickListener(null)

        val set = AnimatorSet()
        set.playTogether(Anims.invisible(content), Anims.invisible(error), Anims.visible(loading))
        set.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                loading.start()
            }
        })
        set.duration = 150
        set.start()
    }

    override fun onTimeout() {
        onQuizCompleted(false)
    }

    private fun goToPokemonScreen() {
        val intent = PokemonActivity.createIntent(this, pokemon!!.id)
        startActivity(intent)
    }

    private fun onQuizCompleted(correctGuess: Boolean) {
        image.clearColorFilter()

        timer.stop()

        val metrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(metrics)
        val screenWidth = metrics.widthPixels.toFloat()
        val guessWidth = guess.width.toFloat()

        val set = AnimatorSet()
        set.playTogether(Anims.invisible(quizControls), Anims.visible(postQuizControls),
                Anims.invisible(failure), Anims.textScale(guess, 1f, screenWidth / guessWidth))
        set.start()

        if (!correctGuess) {
            guess.setText(pokemon!!.name)
        }
        guess.isEnabled = false

        keepPlaying.setOnClickListener({
            guess.setText("")

            val reset = AnimatorSet()
            reset.playTogether(Anims.invisible(postQuizControls), Anims.textScale(guess, 1f))
            reset.duration = 350
            reset.addListener(object: AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    presenter.getNextPokemon()
                }
            })
            reset.start()
        })

        seeDetails.setOnClickListener({
            // goToPokemonScreen()
        })
    }

    private val textChangedListener = object : TextWatcherAdapter() {
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (pokemon != null) {
                if (s.toString() == pokemon!!.name) {
                    onQuizCompleted(true)
                }
            }
        }
    }
}
