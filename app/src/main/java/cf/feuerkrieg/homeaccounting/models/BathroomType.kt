package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class BathroomType(
    val bathroomTypeId: Int,
    val bathroomType1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bathroomTypeId)
        parcel.writeString(bathroomType1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<BathroomType> {
        override fun createFromParcel(parcel: Parcel): BathroomType {
            return BathroomType(parcel)
        }

        override fun newArray(size: Int): Array<BathroomType?> {
            return arrayOfNulls(size)
        }
    }


}