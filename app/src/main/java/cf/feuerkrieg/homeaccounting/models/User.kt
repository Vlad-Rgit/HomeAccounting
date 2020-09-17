package cf.feuerkrieg.homeaccounting.models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
@Entity
data class User(
    val userId: Int = 0,
    var login: String = "",
    var password: String = "",
    var fio: String = "",
    @Transient
    var photo: ByteArray? = null
): Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createByteArray()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(userId)
        parcel.writeString(login)
        parcel.writeString(password)
        parcel.writeString(fio)
        parcel.writeByteArray(photo)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }


}