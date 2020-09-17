package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class Redevelopment(
    val redevelopmentId: Int,
    val redevelopment1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(redevelopmentId)
        parcel.writeString(redevelopment1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Redevelopment> {
        override fun createFromParcel(parcel: Parcel): Redevelopment {
            return Redevelopment(parcel)
        }

        override fun newArray(size: Int): Array<Redevelopment?> {
            return arrayOfNulls(size)
        }
    }

}