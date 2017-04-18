package mb.pokequiz.utils

import android.graphics.RectF

data class Circle(var centerX: Float, var centerY: Float, val radius: Float) {

    var bounds: RectF = RectF()

    init {
        bounds.set(centerX - radius,
                centerY - radius,
                centerX + radius,
                centerY + radius)
    }

    fun contains(x: Float, y: Float): Boolean {
        return Math.pow((centerX - x).toDouble(), 2.0) + Math.pow((centerY - y).toDouble(), 2.0) <= radius * radius
    }

    fun angleInDegrees(x: Float, y: Float): Double {
        return Math.toDegrees(Math.atan2((centerY - y).toDouble(), (centerX - x).toDouble()))
    }

    val diameter: Float
        get() = radius * 2

    companion object {

        fun contains(x: Float, y: Float, radius: Float): Boolean {
            return x * x + y * y <= radius * radius
        }

        fun angle(x: Float, y: Float): Float {
            val radians = Math.atan2(y.toDouble(), x.toDouble())
            return Math.toDegrees(radians).toFloat()
        }
    }
}