package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable

@Serializable
class Bathroom(
    var bathroomId: Int = 0,
    var bathroomType: BathroomType
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readParcelable(BathroomType::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(bathroomId)
        parcel.writeParcelable(bathroomType, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Bathroom> {
        override fun createFromParcel(parcel: Parcel): Bathroom {
            return Bathroom(parcel)
        }

        override fun newArray(size: Int): Array<Bathroom?> {
            return arrayOfNulls(size)
        }
    }


}