package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import cf.feuerkrieg.homeaccounting.database.models.ReasonAbcenseVentilationDatabase
import kotlinx.serialization.Serializable


@Serializable
data class ReasonAbcenseVentilation(
    val reasonAbcenseVentilationId: Int,
    val reasonAbcenseVentilation1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(reasonAbcenseVentilationId)
        parcel.writeString(reasonAbcenseVentilation1)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ReasonAbcenseVentilation> {
        override fun createFromParcel(parcel: Parcel): ReasonAbcenseVentilation {
            return ReasonAbcenseVentilation(parcel)
        }

        override fun newArray(size: Int): Array<ReasonAbcenseVentilation?> {
            return arrayOfNulls(size)
        }
    }

    override fun toString(): String {
        return reasonAbcenseVentilation1
    }

    fun asDatabase(): ReasonAbcenseVentilationDatabase {
        return ReasonAbcenseVentilationDatabase(
            reasonAbcenseVentilationId = this.reasonAbcenseVentilationId,
            reasonAbcenseVentilation = this.reasonAbcenseVentilation1
        )
    }

}