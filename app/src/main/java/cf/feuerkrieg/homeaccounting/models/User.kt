package cf.feuerkrieg.homeaccounting.models

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
    var photo: ByteArray = byteArrayOf()
)