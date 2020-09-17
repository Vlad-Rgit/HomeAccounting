package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class Redevelopment(
    val redevelopmentId: Int,
    val redevelopment1: String
)