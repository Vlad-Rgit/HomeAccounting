package cf.feuerkrieg.homeaccounting.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "window_type")
data class WindowTypeDatabase(
    @PrimaryKey
    var windowTypeId: Int,
    var  windowType: String
)
