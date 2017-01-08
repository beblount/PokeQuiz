package mb.pokequiz.data.json

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mbpeele on 12/25/16.
 */
data class Sprite(
        val back_female: String?,
        val back_shiny_female: String?,
        val back_default: String?,
        val front_female: String?,
        val front_shiny_female: String?,
        val back_shiny: String?,
        val front_default: String?,
        val front_shiny: String?) : Parcelable {

    fun getFront(): String? {
        return firstNonNull(front_female, front_shiny_female, front_default, front_shiny)
    }

    private fun firstNonNull(vararg args: String?) : String? {
        return args.firstOrNull { it != null }
    }

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Sprite> = object : Parcelable.Creator<Sprite> {
            override fun createFromParcel(source: Parcel): Sprite = Sprite(source)
            override fun newArray(size: Int): Array<Sprite?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(back_female)
        dest?.writeString(back_shiny_female)
        dest?.writeString(back_default)
        dest?.writeString(front_female)
        dest?.writeString(front_shiny_female)
        dest?.writeString(back_shiny)
        dest?.writeString(front_default)
        dest?.writeString(front_shiny)
    }
}