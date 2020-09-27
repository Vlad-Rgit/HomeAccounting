package cf.feuerkrieg.homeaccounting.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cf.feuerkrieg.homeaccounting.models.DraftVentilation

@Entity(tableName = "draft_ventilation")
data class DraftVentilationDatabase(
    @PrimaryKey
    var draftVentilationId: Int,
    var draftVentilation: String
) {
    fun asModel(): DraftVentilation {
        return DraftVentilation(
            draftVentilationId = this.draftVentilationId,
            draftVentilation1 = this.draftVentilation
        )
    }
}