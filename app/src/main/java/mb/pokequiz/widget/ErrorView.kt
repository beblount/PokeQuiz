package mb.pokequiz.widget

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import mb.pokequiz.R
import mb.pokequiz.databinding.ErrorBinding

/**
 * Created by mbpeele on 4/17/17.
 */
class ErrorView : LinearLayout {

    private var binding: ErrorBinding

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    init {
        val inflater = LayoutInflater.from(context)
        binding = DataBindingUtil.inflate(inflater, R.layout.error, this, true)
    }

    fun set(@DrawableRes drawableRes: Int, message: String) {
        binding.image.setImageDrawable(context.getDrawable(drawableRes))
        binding.message.text = message
    }

    fun set(@DrawableRes drawableRes: Int, @StringRes stringRes: Int) {
        binding.image.setImageDrawable(context.getDrawable(drawableRes))
        binding.message.text = context.getString(stringRes)
    }
}