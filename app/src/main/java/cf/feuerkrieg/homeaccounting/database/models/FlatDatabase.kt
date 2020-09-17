package cf.feuerkrieg.homeaccounting.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cf.feuerkrieg.homeaccounting.serializers.TimestampSerializer
import kotlinx.serialization.Serializable
import java.sql.Timestamp

@Entity(tableName = "flat")
data class FlatDatabase(
    @PrimaryKey
    var flatId: Int = 0,
    var entrance: Int = 1,
    var floor: Int = 1,
    var number: Int = 1,
    var post: Int = 1,
    var hasAccess: Byte = 0,
    @Serializable(with = TimestampSerializer::class)
    var dateCreated: Timestamp? = null,
    var signature: ByteArray? = null,
    var homeId: Int,
    var userId: Int
)