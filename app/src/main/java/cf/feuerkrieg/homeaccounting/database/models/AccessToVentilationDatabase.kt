package cf.feuerkrieg.homeaccounting.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cf.feuerkrieg.homeaccounting.models.AccessToVentilation

@Entity(tableName = "access_to_ventilation")
data class AccessToVentilationDatabase(
    @PrimaryKey
    var accessToVentilationId: Int,
    var accessToVentilation: String
) {

    fun asModel(): AccessToVentilation {
        return AccessToVentilation(
            accesToVentilationId = this.accessToVentilationId,
            accesToVentilation1 = this.accessToVentilation
        )
    }

}