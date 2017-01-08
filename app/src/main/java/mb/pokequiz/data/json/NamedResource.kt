package mb.pokequiz.data.json

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mbpeele on 12/25/16.
 */
data class NamedResource(
        val name: String,
        val url: String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<NamedResource> = object : Parcelable.Creator<NamedResource> {
            override fun createFromParcel(source: Parcel): NamedResource = NamedResource(source)
            override fun newArray(size: Int): Array<NamedResource?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readString(), source.readString())

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(name)
        dest?.writeString(url)
    }
}