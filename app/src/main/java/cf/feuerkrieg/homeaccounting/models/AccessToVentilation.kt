package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import cf.feuerkrieg.homeaccounting.database.models.AccessToVentilationDatabase
import kotlinx.serialization.Serializable

@Serializable
data class AccessToVentilation(
    val accesToVentilationId: Int,
    val accesToVentilation1: String
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(accesToVentilationId)
        parcel.writeString(accesToVentilation1)
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

    fun asDatabase(): AccessToVentilationDatabase {
        return AccessToVentilationDatabase(
            accessToVentilationId = this.accesToVentilationId,
            accessToVentilation = this.accesToVentilation1
        )
    }

    override fun toString(): String {
        return accesToVentilation1
    }
}