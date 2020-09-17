package cf.feuerkrieg.homeaccounting.models

import cf.feuerkrieg.homeaccounting.database.models.FlatDatabase
import cf.feuerkrieg.homeaccounting.interfaces.SortedItem
import cf.feuerkrieg.homeaccounting.serializers.TimestampSerializer
import kotlinx.serialization.Serializable
import java.sql.Timestamp


@Serializable
data class Flat(
    var flatId: Int = 0,
    var entrance: Int = 1,
    var floor: Int = 1,
    var number: Int = 1,
    var post: Int = 1,
    var hasAccess: Byte = 0,
    @Serializable(with = TimestampSerializer::class)
    var dateCreated: Timestamp? = null,
    var signature: ByteArray? = null,
    var home: Home,
    var user: User,
    var bathroom: Bathroom? = null,
    var kitchen: Kitchen? = null,
) : SortedItem {

    override fun areItemsTheSame(item: SortedItem): Boolean {

        if(item as? Flat != null) {
            return flatId == item.flatId
        }
        else {
            throw IllegalArgumentException("Sorted Item is not a flat")
        }
    }

    override fun areContentsTheSame(item: SortedItem): Boolean {
        if(item as? Flat != null) {
            return this == item
        }
        else {
            throw IllegalArgumentException("Sorted Item is not a flat")
        }
    }

    override fun compareTo(other: SortedItem): Int {
        if(other as? Flat != null) {
            return this.flatId.compareTo(other.flatId)
        }
        else {
            throw IllegalArgumentException("Sorted Item is not a flat")
        }
    }
}



fun List<Flat>.asDatabase(): List<FlatDatabase> {
    return this.map {
        FlatDatabase(it.flatId,
                it.entrance,
                it.floor,
                it.number,
                it.post,
                it.hasAccess,
                it.dateCreated,
                it.signature,
                it.home.homeId,
                it.user.userId)
    }
}