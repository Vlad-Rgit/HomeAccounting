package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable

@Serializable
data class AccessToVentilation(
    val accessToVentilationId: Int,
    val accessToVentilation1: String
)