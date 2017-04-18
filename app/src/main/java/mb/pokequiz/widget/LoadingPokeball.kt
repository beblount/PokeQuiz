package mb.pokequiz.widget

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import mb.pokequiz.utils.Circle

/**
 * Created by mbpeele on 4/17/17.
 */
class LoadingPokeball : View {

    val paint : Paint = Paint()
    var circle : Circle = Circle(0F, 0F, 0F)
    val arcAnimator : ValueAnimator = ValueAnimator.ofFloat(0F, 180F)

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        paint.color = Color.WHITE
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = 10f
    }

    @SuppressLint("DrawAllocation")
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        setMeasuredDimension(width, width)
        val center = width * .5F
        val radius = width * .3F

        circle = Circle(center, center, radius)

        arcAnimator.repeatCount = ValueAnimator.INFINITE
        arcAnimator.repeatMode = ValueAnimator.REVERSE
        arcAnimator.addUpdateListener {
            invalidate()
        }
        arcAnimator.start()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val animatedValue = arcAnimator.animatedValue as Float

        paint.color = Color.BLACK
        canvas.drawArc(circle.boundingRect, 180F, animatedValue, false, paint)

        paint.color = Color.WHITE
        canvas.drawArc(circle.boundingRect, 0F, animatedValue, false, paint)

//        paint.color = Color.GRAY
//        canvas.drawCircle(circle.centerX, circle.centerY, circle.radius, paint)
    }

    fun stop() {

    }

    fun start() {

    }
}