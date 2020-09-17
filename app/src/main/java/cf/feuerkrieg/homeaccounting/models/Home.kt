package cf.feuerkrieg.homeaccounting.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import cf.feuerkrieg.homeaccounting.interfaces.SortedItem
import kotlinx.serialization.Serializable

@Entity(tableName = "home")
@Serializable
data class Home(
    var name: String,
    @PrimaryKey
    var homeId: Int = 0
) : SortedItem {

    override fun areItemsTheSame(item: SortedItem): Boolean {

        val home = item as Home

        return name == home.name
    }

    override fun areContentsTheSame(item: SortedItem): Boolean {

        val home = item as Home

        return name == home.name
    }

    override fun compareTo(other: SortedItem): Int {

        val home = other as Home

        return this.name.compareTo(home.name)
    }

}