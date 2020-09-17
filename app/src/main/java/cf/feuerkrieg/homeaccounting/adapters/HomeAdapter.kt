package cf.feuerkrieg.homeaccounting.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import cf.feuerkrieg.homeaccounting.callbacks.SortedListCallback
import cf.feuerkrieg.homeaccounting.databinding.ItemHomeLayoutBinding
import cf.feuerkrieg.homeaccounting.models.Home
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeAdapter(val context: Context,
                  private val onHomeClickListener: (home: Home) -> Unit)
    : RecyclerView.Adapter<HomeAdapter.ViewHolder>(),
      ReplacableAdapter<Home>
{

    private val sortedList = SortedList<Home>(Home::class.java,
                                                SortedListCallback(this))

    private val inflater: LayoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemHomeLayoutBinding.inflate(
            inflater, parent, false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.performBind(sortedList[position], onHomeClickListener)
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    class ViewHolder(val binding: ItemHomeLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun performBind(home: Home,
                        clickListener: (home: Home) -> Unit) {
            binding.home = home
            binding.homeCard.setOnClickListener {
                clickListener(home)
            }
        }
    }

    suspend fun filterByName(baseHomes: MutableList<Home>, name: String) {

        withContext(Dispatchers.Default) {

            for(home in baseHomes.toList()) {
                if(!home.name.contains(name)) {
                    baseHomes.remove(home)
                }
            }

            sortedList.replaceAll(baseHomes)
        }
    }

    override fun replaceAll(homes: Collection<Home>) {
        sortedList.replaceAll(homes)
    }

}