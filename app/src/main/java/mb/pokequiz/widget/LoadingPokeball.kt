package mb.pokequiz.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import mb.pokequiz.R
import mb.pokequiz.utils.Anims
import mb.pokequiz.utils.Anims.floatAnimatedValue
import mb.pokequiz.utils.Circle

/**
 * Created by mbpeele on 4/17/17.
 */
class LoadingPokeball : View {

    private val TAG : String = LoadingPokeball::class.simpleName!!

    val paint : Paint = Paint()

    var circle : Circle = Circle(0F, 0F, 0F)
    val innerRect : RectF = RectF()

    val shakeAnimator : ValueAnimator = ValueAnimator.ofFloat(0F, -30F, 30F)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 25F
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        setMeasuredDimension(width, width)

        val center = width * .5F
        val radius = width * .3F
        val padding = radius * .7F

        circle = Circle(center, center, radius)

        val cx = circle.centerX
        val cy = circle.centerY
        val dimen = radius - padding
        innerRect.set(cx - dimen, cy - dimen, cx + dimen, cy + dimen)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        canvas.save()
        val shakeValue = shakeAnimator.floatAnimatedValue()
        canvas.rotate(shakeValue, circle.centerX, circle.bounds.height())

        // White arc
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawArc(circle.bounds, 0f, 180F, true, paint)

        // draw background color to get rid of stupid Arc white
        paint.color = ContextCompat.getColor(context, R.color.primary)
        canvas.drawCircle(circle.centerX, circle.centerY, innerRect.width() / 2F, paint)

        // Inner white circle
        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL
        canvas.drawCircle(circle.centerX, circle.centerY, innerRect.width() / 2F, paint)

        // Inner stroke circle
        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(circle.centerX, circle.centerY, innerRect.width() / 2F, paint)

        // Inner filled circle
        paint.color = Color.BLACK
        paint.style = Paint.Style.FILL
        canvas.drawCircle(circle.centerX, circle.centerY, innerRect.width() / 4F, paint)

        // Outer circle
        paint.style = Paint.Style.STROKE
        canvas.drawCircle(circle.centerX, circle.centerX, circle.radius, paint)

        // Line
        canvas.drawLine(circle.bounds.left, circle.centerY, circle.centerX - innerRect.width() / 2F, circle.centerY, paint)
        canvas.drawLine(circle.bounds.right, circle.centerY, circle.centerX + innerRect.width() / 2F, circle.centerX, paint)

        canvas.restore()
    }

    fun stop() {
        shakeAnimator.cancel()
        invalidate()
    }

    fun start() {
        shakeAnimator.duration = 1000
        shakeAnimator.interpolator = Anims.LINEAR
        shakeAnimator.repeatCount = ValueAnimator.INFINITE
        shakeAnimator.repeatMode = ValueAnimator.REVERSE
        shakeAnimator.addListener(object: AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator?) {
                invalidate()
            }
        })
        shakeAnimator.addUpdateListener {
            invalidate()
        }
        shakeAnimator.start()
    }
}