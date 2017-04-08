package mb.pokequiz.quiz

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import mb.pokequiz.R
import mb.pokequiz.utils.FontUtils
import java.util.concurrent.TimeUnit


/**
 * Created by mbpeele on 12/28/16.
 */

class Timer : View {

    lateinit var paint : Paint
    val rect : Rect = Rect()

    var disposable : Disposable? = null
    var value : Int = 0
    var shouldShow : Boolean = false

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    fun init(context: Context) {
        if (isInEditMode) {
            return
        }

        val attrs = intArrayOf(android.R.attr.textColorPrimary)
        val array = context.theme.obtainStyledAttributes(R.style.Poke, attrs)
        val color = array.getColor(0, Color.BLACK)
        array.recycle()

        paint = Paint()
        paint.color = color
        paint.textAlign = Paint.Align.CENTER
        paint.typeface = FontUtils[context, R.string.pokemon_bit]
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        if (isInEditMode) {
            return
        }

        paint.textSize = w / 4f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isInEditMode) {
            return
        }

        if (shouldShow) {
            val text = value.toString()
            paint.getTextBounds(text, 0, text.length, rect)
            val textHeight = rect.height()

            canvas.drawText(text,
                    canvas.width / 2f,
                    canvas.height / 2f + textHeight / 2f,
                    paint)
        }
    }

    fun start(int: Int, timerListener: TimerListener) {
        value = int + 1

        shouldShow = true

        disposable = Observable.interval(1, TimeUnit.SECONDS)
                .takeWhile { value > 0 }
                .map { value -= 1 }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    invalidate()
                }, {
                   // who cares
                }, {
                    timerListener.onTimeout()
                })
    }

    fun stop() {
        shouldShow = false

        disposable?.dispose()
    }

    interface TimerListener {

        fun onTimeout()

    }
}
