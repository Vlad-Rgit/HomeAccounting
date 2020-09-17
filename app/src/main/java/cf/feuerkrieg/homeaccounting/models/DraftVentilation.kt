package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class DraftVentilation(
    val draftVentilationId: Int,
    val draftVentilation1: String
)