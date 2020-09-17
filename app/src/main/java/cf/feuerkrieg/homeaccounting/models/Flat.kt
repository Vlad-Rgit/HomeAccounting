package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import cf.feuerkrieg.homeaccounting.database.models.FlatDatabase
import cf.feuerkrieg.homeaccounting.interfaces.SortedItem
import cf.feuerkrieg.homeaccounting.serializers.TimestampSerializer
import kotlinx.serialization.Serializable
import java.sql.Timestamp


@Serializable
data class Flat(
    var flatId: Int = 0,
    var entrance: Int = 1,
    var floor: Int = 1,
    var number: Int = 1,
    var post: Int = 1,
    var hasAccess: Byte = 0,
    @Serializable(with = TimestampSerializer::class)
    var dateCreated: Timestamp? = null,
    var signature: ByteArray? = null,
    var home: Home,
    var user: User,
    var bathroom: Bathroom? = null,
    var kitchen: Kitchen? = null,
) : SortedItem, Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readByte(),
        TODO("dateCreated"),
        parcel.createByteArray(),
        parcel.readParcelable(Home::class.java.classLoader)!!,
        parcel.readParcelable(User::class.java.classLoader)!!,
        parcel.readParcelable(Bathroom::class.java.classLoader),
        parcel.readParcelable(Kitchen::class.java.classLoader)
    )

    override fun areItemsTheSame(item: SortedItem): Boolean {

        if(item as? Flat != null) {
            return flatId == item.flatId
        }
        else {
            throw IllegalArgumentException("Sorted Item is not a flat")
        }
    }

    override fun areContentsTheSame(item: SortedItem): Boolean {
        if(item as? Flat != null) {
            return this == item
        }
        else {
            throw IllegalArgumentException("Sorted Item is not a flat")
        }
    }

    override fun compareTo(other: SortedItem): Int {
        if(other as? Flat != null) {
            return this.flatId.compareTo(other.flatId)
        }
        else {
            throw IllegalArgumentException("Sorted Item is not a flat")
        }
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(flatId)
        parcel.writeInt(entrance)
        parcel.writeInt(floor)
        parcel.writeInt(number)
        parcel.writeInt(post)
        parcel.writeByte(hasAccess)
        parcel.writeByteArray(signature)
        parcel.writeParcelable(home, flags)
        parcel.writeParcelable(user, flags)
        parcel.writeParcelable(bathroom, flags)
        parcel.writeParcelable(kitchen, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Flat> {
        override fun createFromParcel(parcel: Parcel): Flat {
            return Flat(parcel)
        }

        override fun newArray(size: Int): Array<Flat?> {
            return arrayOfNulls(size)
        }
    }
}



fun List<Flat>.asDatabase(): List<FlatDatabase> {
    return this.map {
        FlatDatabase(it.flatId,
                it.entrance,
                it.floor,
                it.number,
                it.post,
                it.hasAccess,
                it.dateCreated,
                it.signature,
                it.home.homeId,
                it.user.userId)
    }
}