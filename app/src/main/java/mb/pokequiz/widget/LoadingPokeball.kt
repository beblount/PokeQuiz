package mb.pokequiz.widget

import android.animation.AnimatorSet
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

    val outerArcAnimator: ValueAnimator = ValueAnimator.ofFloat(0F, 180F)
    val lineAnimator : ValueAnimator = ValueAnimator()
    val innerArcAnimator : ValueAnimator = ValueAnimator.ofFloat(0F, 180F)
    val innerRadiusAnimator : ValueAnimator = ValueAnimator()
    val whiteArcAnimator : ValueAnimator = ValueAnimator.ofFloat(0F, 180F)
    val pulseAnimator : ValueAnimator = ValueAnimator()
    var animatorSet : AnimatorSet ?= null

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

        paint.color = Color.WHITE
        paint.style = Paint.Style.FILL_AND_STROKE
        val whiteArcAngle = whiteArcAnimator.floatAnimatedValue()
        if (whiteArcAngle > 0F) {
            canvas.drawArc(circle.bounds, 0F, whiteArcAngle, true, paint)
        }

        // draw background color to get rid of stupid Arc white
        paint.color = ContextCompat.getColor(context, R.color.primary)
        canvas.drawCircle(circle.centerX, circle.centerY, innerRect.width() / 2F, paint)

        paint.color = Color.BLACK
        paint.style = Paint.Style.STROKE
        val outerSweepAngle = outerArcAnimator.floatAnimatedValue()
        if (outerSweepAngle > 0F) {
            canvas.drawArc(circle.bounds, 180F, outerSweepAngle, false, paint)
            canvas.drawArc(circle.bounds, 0F, outerSweepAngle, false, paint)
        }

        paint.strokeWidth = 25F
        val lineValue = lineAnimator.floatAnimatedValue()
        if (lineValue > 0F) {
            canvas.drawLine(circle.bounds.left, circle.centerY, circle.bounds.left + lineValue, circle.centerY, paint)
            canvas.drawLine(circle.bounds.right, circle.centerY, circle.bounds.right - lineValue, circle.centerY, paint)
        }

        val innerSweepAngle = innerArcAnimator.floatAnimatedValue()
        if (innerSweepAngle > 0F) {
            canvas.drawArc(innerRect, 180F, innerSweepAngle, false, paint)
            canvas.drawArc(innerRect, 0F, innerSweepAngle, false, paint)
        }

        paint.style = Paint.Style.FILL
        paint.color = pulseAnimator.animatedValue as Int
        val innerRadius = innerRadiusAnimator.floatAnimatedValue()
        if (innerRadius > 0F) {
            canvas.drawCircle(circle.centerX, circle.centerY, innerRadius, paint)
        }
    }

    fun stop() {
        animatorSet!!.cancel()
        invalidate()
    }

    fun start() {
        outerArcAnimator.interpolator = Anims.FAST_OUT_SLOW_IN
        outerArcAnimator.duration = 1000
        outerArcAnimator.addUpdateListener {
            invalidate()
        }

        lineAnimator.setFloatValues(0F, circle.radius * .7F)
        lineAnimator.interpolator = Anims.FAST_OUT_SLOW_IN
        lineAnimator.duration = 650
        lineAnimator.addUpdateListener {
            invalidate()
        }

        val outerAndLine = AnimatorSet()
        outerAndLine.playSequentially(outerArcAnimator, lineAnimator)

        innerArcAnimator.interpolator = Anims.FAST_OUT_SLOW_IN
        innerArcAnimator.duration = 450
        innerArcAnimator.addUpdateListener {
            invalidate()
        }

        innerRadiusAnimator.interpolator = Anims.OVERSHOOT
        innerRadiusAnimator.setFloatValues(0F, innerRect.width() / 4F)
        innerRadiusAnimator.duration = innerArcAnimator.duration
        innerRadiusAnimator.addUpdateListener {
            invalidate()
        }

        whiteArcAnimator.interpolator = Anims.FAST_OUT_SLOW_IN
        whiteArcAnimator.duration = innerArcAnimator.duration
        whiteArcAnimator.addUpdateListener {
            invalidate()
        }

        val inner = AnimatorSet()
        inner.playTogether(innerArcAnimator, innerRadiusAnimator, whiteArcAnimator)

        pulseAnimator.setIntValues(Color.WHITE,
                ContextCompat.getColor(context, R.color.primary_dark),
                ContextCompat.getColor(context, R.color.accent))
        pulseAnimator.setEvaluator(Anims.ARGB_EVAL)
        pulseAnimator.repeatCount = ValueAnimator.INFINITE
        pulseAnimator.repeatMode = ValueAnimator.REVERSE
        pulseAnimator.duration = 450
        pulseAnimator.addUpdateListener {
            invalidate()
        }

        animatorSet = AnimatorSet()
        animatorSet!!.playSequentially(outerAndLine, inner, pulseAnimator)
        animatorSet!!.start()
    }
}