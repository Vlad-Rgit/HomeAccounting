package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
data class AccessToVentilation(
    val accessToVentilationId: Int,
    val accessToVentilation1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(accessToVentilationId)
        parcel.writeString(accessToVentilation1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<AccessToVentilation> {
        override fun createFromParcel(parcel: Parcel): AccessToVentilation {
            return AccessToVentilation(parcel)
        }

        override fun newArray(size: Int): Array<AccessToVentilation?> {
            return arrayOfNulls(size)
        }
    }

}