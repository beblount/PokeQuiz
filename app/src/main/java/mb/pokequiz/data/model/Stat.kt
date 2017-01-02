package mb.pokequiz.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mbpeele on 12/25/16.
 */
data class Stat(
        val base_stat: Int,
        val effort: Int,
        val stat: NamedResource) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Stat> = object : Parcelable.Creator<Stat> {
            override fun createFromParcel(source: Parcel): Stat = Stat(source)
            override fun newArray(size: Int): Array<Stat?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readInt(), source.readParcelable<NamedResource>(NamedResource::class.java.classLoader))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(base_stat)
        dest?.writeInt(effort)
        dest?.writeParcelable(stat, 0)
    }
}