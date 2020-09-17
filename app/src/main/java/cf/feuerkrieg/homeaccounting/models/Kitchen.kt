package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable


@Serializable
data class Kitchen(
    var flatId: Int = 0,
    var hasSupplyDevice: Byte = 0,
    var hasChannel: Byte = 0,
    var accessToVentilation: AccessToVentilation,
    var redevelopment: Redevelopment,
    var windowType: WindowType,
    var ventilation: Ventilation?,
    var reasonAbcenses: MutableList<ReasonAbcenseVentilation>?
)