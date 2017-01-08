package mb.pokequiz.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mbpeele on 12/25/16.
 */
data class Ability(
        val is_hidden: Boolean,
        val slot: Int,
        val ability: NamedResource) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Ability> = object : Parcelable.Creator<Ability> {
            override fun createFromParcel(source: Parcel): Ability = Ability(source)
            override fun newArray(size: Int): Array<Ability?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(1.equals(source.readInt()), source.readInt(), source.readParcelable<NamedResource>(NamedResource::class.java.classLoader))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt((if (is_hidden) 1 else 0))
        dest?.writeInt(slot)
        dest?.writeParcelable(ability, 0)
    }
}