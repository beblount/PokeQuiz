package mb.pokequiz.data.json

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mbpeele on 12/25/16.
 */
data class Type(
        val slot: Int,
        val type: NamedResource) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Type> = object : Parcelable.Creator<Type> {
            override fun createFromParcel(source: Parcel): Type = Type(source)
            override fun newArray(size: Int): Array<Type?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readParcelable<NamedResource>(NamedResource::class.java.classLoader))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(slot)
        dest?.writeParcelable(type, 0)
    }
}