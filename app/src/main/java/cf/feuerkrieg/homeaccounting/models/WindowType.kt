package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class WindowType(
    val windowTypeId: Int,
    val windowType1: String)