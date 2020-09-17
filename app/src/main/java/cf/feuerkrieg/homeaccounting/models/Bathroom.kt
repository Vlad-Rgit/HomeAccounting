package cf.feuerkrieg.homeaccounting.models

import kotlinx.serialization.Serializable

@Serializable
class Bathroom(
    var bathroomId: Int = 0,
    var bathroomType: BathroomType
)