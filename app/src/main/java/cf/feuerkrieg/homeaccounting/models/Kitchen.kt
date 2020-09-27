package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import kotlinx.serialization.Serializable


@Serializable
data class Kitchen(
    var flatId: Int = 0,
    var hasSupplyDevice: Byte = 0,
    var hasChannel: Byte = 0,
    var accessToVentilation: AccessToVentilation? = null,
    var redevelopment: Redevelopment? = null,
    var windowType: WindowType? = null,
    var ventilation: Ventilation? = null,
    var reasonAbcenses: MutableList<ReasonAbcenseVentilation> = mutableListOf()
): Parcelable {

    constructor(parcel: Parcel) : this(

        parcel.readInt(),
        parcel.readByte(),
        parcel.readByte(),
        parcel.readParcelable(AccessToVentilation::class.java.classLoader)!!,
        parcel.readParcelable(Redevelopment::class.java.classLoader)!!,
        parcel.readParcelable(WindowType::class.java.classLoader)!!,
        parcel.readParcelable(Ventilation::class.java.classLoader),

        parcel.readParcelableArray(ReasonAbcenseVentilation::class.java.classLoader)
            ?.map {
                it as ReasonAbcenseVentilation
            }
            ?.toMutableList()!!
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(flatId)
        parcel.writeByte(hasSupplyDevice)
        parcel.writeByte(hasChannel)
        parcel.writeParcelable(accessToVentilation, flags)
        parcel.writeParcelable(redevelopment, flags)
        parcel.writeParcelable(windowType, flags)
        parcel.writeParcelable(ventilation, flags)
        parcel.writeParcelableArray(reasonAbcenses.toTypedArray(), flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Kitchen> {
        override fun createFromParcel(parcel: Parcel): Kitchen {
            return Kitchen(parcel)
        }

        override fun newArray(size: Int): Array<Kitchen?> {
            return arrayOfNulls(size)
        }
    }

}