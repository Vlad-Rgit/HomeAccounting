package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class BathroomType(
    val bathroomTypeId: Int,
    val bathroomType1: String
)