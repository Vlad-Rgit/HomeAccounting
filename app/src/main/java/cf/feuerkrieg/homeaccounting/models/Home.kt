package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import cf.feuerkrieg.homeaccounting.interfaces.SortedItem
import kotlinx.serialization.Serializable

@Entity(tableName = "home")
@Serializable
data class Home(
    var name: String,
    @PrimaryKey
    var homeId: Int = 0
) : SortedItem, Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readInt()
    )

    override fun areItemsTheSame(item: SortedItem): Boolean {

        val home = item as Home

        return name == home.name
    }

    override fun areContentsTheSame(item: SortedItem): Boolean {

        val home = item as Home

        return name == home.name
    }

    override fun compareTo(other: SortedItem): Int {

        val home = other as Home

        return this.name.compareTo(home.name)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeInt(homeId)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Home> {
        override fun createFromParcel(parcel: Parcel): Home {
            return Home(parcel)
        }

        override fun newArray(size: Int): Array<Home?> {
            return arrayOfNulls(size)
        }
    }

}