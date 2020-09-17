package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class ReasonAbcenseVentilation(
    val reasonAbcenseVentilationId: Int,
    val reasonAbcenseVentilation1: String
)