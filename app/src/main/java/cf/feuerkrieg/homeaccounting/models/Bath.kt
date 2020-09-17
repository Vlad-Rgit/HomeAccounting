package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class Bath(
    var bathroomId: Int = 0,
    var hasChannel: Byte = 0,
    var widthChannel: Float = 1f,
    var heightChannel: Float = 1f,
    var anemonetrValue: Float = 0.1f,
    var accessToVentilation: AccessToVentilation,
    var draftVentilation: DraftVentilation?)