package cf.feuerkrieg.homeaccounting.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "redevelopment")
data class RedevelopmentDatabase(
    @PrimaryKey
    var redevelopmentId: Int,
    var redevelopment: String
)