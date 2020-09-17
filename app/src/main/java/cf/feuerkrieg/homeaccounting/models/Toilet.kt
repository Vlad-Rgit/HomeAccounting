package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class Toilet(
    var bathroomId: Int = 0,
    var hasChannel: Byte = 0,
    var widthChannel: Float = 1f,
    var heightChannel: Float = 1f,
    var accessToVentilation: AccessToVentilation,
    var draftVentilation: DraftVentilation? = null,
    var reasonAbcenses: MutableList<ReasonAbcenseToilet>?
)