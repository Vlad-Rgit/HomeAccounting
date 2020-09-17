package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class Ventilation(
    var kitchenId: Int = 0,
    var hasDefects: Byte = 0,
    var heightChannel: Float = 1f,
    var widthChannel: Float = 1f,
    var anemometrValue: Float = 0.1f,
    var draftVentilation: DraftVentilation
)