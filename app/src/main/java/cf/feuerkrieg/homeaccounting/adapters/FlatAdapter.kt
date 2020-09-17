package cf.feuerkrieg.homeaccounting.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SortedList
import cf.feuerkrieg.homeaccounting.BR
import cf.feuerkrieg.homeaccounting.callbacks.SortedListCallback
import cf.feuerkrieg.homeaccounting.databinding.ItemFlatRowLayoutBinding
import cf.feuerkrieg.homeaccounting.models.Flat

class FlatAdapter :
    RecyclerView.Adapter<FlatAdapter.ViewHolder>(),
    ReplacableAdapter<Flat>{

    private val sortedList = SortedList(Flat::class.java,
        SortedListCallback<Flat>(this))

    var flatViewType: FlatViewType = FlatViewType.ROW

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(parent.context)

        val binding: ViewDataBinding

        when(flatViewType) {
            FlatViewType.ROW ->
                binding = ItemFlatRowLayoutBinding.inflate(
                    inflater, parent, false)
            else -> throw IllegalArgumentException("FlatViewType ${flatViewType.value}" +
                    " does not correspond any enum value")
        }


        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val flat = sortedList[position]

        holder.performBind(flat)
    }

    override fun getItemCount(): Int {
        return sortedList.size()
    }

    override fun replaceAll(flats: Collection<Flat>) {
        sortedList.replaceAll(flats)
    }

    class ViewHolder(private val binding: ViewDataBinding)
        : RecyclerView.ViewHolder(binding.root) {

        fun performBind(flat: Flat) {
            binding.setVariable(BR.flat, flat)
        }

    }

    enum class FlatViewType(val value: String) {
        ROW("Список"),
        CARD("Карточки")
    }

}