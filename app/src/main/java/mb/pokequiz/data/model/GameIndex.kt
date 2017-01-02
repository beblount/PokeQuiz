package mb.pokequiz.data.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by mbpeele on 12/25/16.
 */
data class GameIndex(
        val game_index: Int,
        val version: NamedResource) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<GameIndex> = object : Parcelable.Creator<GameIndex> {
            override fun createFromParcel(source: Parcel): GameIndex = GameIndex(source)
            override fun newArray(size: Int): Array<GameIndex?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readParcelable<NamedResource>(NamedResource::class.java.classLoader))

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(game_index)
        dest?.writeParcelable(version, 0)
    }
}