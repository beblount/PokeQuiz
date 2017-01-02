package mb.pokequiz.data.model

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by mbpeele on 12/25/16.
 */
data class Pokemon(
        val id: Int,
        val name: String,
        val base_experience: Int,
        val height: Int,
        val is_default: Boolean,
        val order: Int,
        val weight: Int,
        val location_area_encounters: String,
        val sprites: Sprite,
        val species: NamedResource,
        val abilities: List<Ability>,
        val forms: List<NamedResource>,
        val game_indices: List<GameIndex>,
        val stats: List<Stat>,
        val types: List<Type>) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Pokemon> = object : Parcelable.Creator<Pokemon> {
            override fun createFromParcel(source: Parcel): Pokemon = Pokemon(source)
            override fun newArray(size: Int): Array<Pokemon?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(source.readInt(), source.readString(), source.readInt(), source.readInt(), 1.equals(source.readInt()), source.readInt(), source.readInt(), source.readString(), source.readParcelable<Sprite>(Sprite::class.java.classLoader), source.readParcelable<NamedResource>(NamedResource::class.java.classLoader), ArrayList<Ability>().apply{ source.readList(this, Ability::class.java.classLoader) }, source.createTypedArrayList(NamedResource.CREATOR), source.createTypedArrayList(GameIndex.CREATOR), source.createTypedArrayList(Stat.CREATOR), ArrayList<Type>().apply{ source.readList(this, Type::class.java.classLoader) })

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeInt(id)
        dest?.writeString(name)
        dest?.writeInt(base_experience)
        dest?.writeInt(height)
        dest?.writeInt((if (is_default) 1 else 0))
        dest?.writeInt(order)
        dest?.writeInt(weight)
        dest?.writeString(location_area_encounters)
        dest?.writeParcelable(sprites, 0)
        dest?.writeParcelable(species, 0)
        dest?.writeList(abilities)
        dest?.writeTypedList(forms)
        dest?.writeTypedList(game_indices)
        dest?.writeTypedList(stats)
        dest?.writeList(types)
    }
}