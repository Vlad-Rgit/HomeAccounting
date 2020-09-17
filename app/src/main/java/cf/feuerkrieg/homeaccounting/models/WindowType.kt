package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class WindowType(
    val windowTypeId: Int,
    val windowType1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(windowTypeId)
        parcel.writeString(windowType1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<WindowType> {
        override fun createFromParcel(parcel: Parcel): WindowType {
            return WindowType(parcel)
        }

        override fun newArray(size: Int): Array<WindowType?> {
            return arrayOfNulls(size)
        }
    }

}