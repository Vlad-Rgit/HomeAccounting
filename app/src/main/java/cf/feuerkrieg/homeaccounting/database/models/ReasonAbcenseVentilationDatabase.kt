package cf.feuerkrieg.homeaccounting.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cf.feuerkrieg.homeaccounting.models.ReasonAbcenseVentilation

@Entity(tableName = "reason_abcense_ventilation")
data class ReasonAbcenseVentilationDatabase(
    @PrimaryKey
    var reasonAbcenseVentilationId: Int,
    var reasonAbcenseVentilation: String
) {

    fun asModel(): ReasonAbcenseVentilation {
        return ReasonAbcenseVentilation(
            reasonAbcenseVentilationId = this.reasonAbcenseVentilationId,
            reasonAbcenseVentilation1 = this.reasonAbcenseVentilation
        )
    }
}