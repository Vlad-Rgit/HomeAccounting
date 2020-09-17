package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class ReasonAbcenseToilet(
    val reasonAbcenseToiletId: Int,
    val reasonAbcenseToilet1: String
)