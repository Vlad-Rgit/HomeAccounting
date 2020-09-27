package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class Ventilation(
    var kitchenId: Int = 0,
    var hasDefects: Byte = 0,
    var heightChannel: Float = 1f,
    var widthChannel: Float = 1f,
    var anemometrValue: Float = 0.1f,
    var draftVentilation: DraftVentilation? = null
): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readByte(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readFloat(),
        parcel.readParcelable(DraftVentilation::class.java.classLoader)!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(kitchenId)
        parcel.writeByte(hasDefects)
        parcel.writeFloat(heightChannel)
        parcel.writeFloat(widthChannel)
        parcel.writeFloat(anemometrValue)
        parcel.writeParcelable(draftVentilation, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Ventilation> {
        override fun createFromParcel(parcel: Parcel): Ventilation {
            return Ventilation(parcel)
        }

        override fun newArray(size: Int): Array<Ventilation?> {
            return arrayOfNulls(size)
        }
    }

}