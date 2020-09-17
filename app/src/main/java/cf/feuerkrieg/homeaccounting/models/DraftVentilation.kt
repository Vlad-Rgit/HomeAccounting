package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class DraftVentilation(
    val draftVentilationId: Int,
    val draftVentilation1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(draftVentilationId)
        parcel.writeString(draftVentilation1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DraftVentilation> {
        override fun createFromParcel(parcel: Parcel): DraftVentilation {
            return DraftVentilation(parcel)
        }

        override fun newArray(size: Int): Array<DraftVentilation?> {
            return arrayOfNulls(size)
        }
    }

}