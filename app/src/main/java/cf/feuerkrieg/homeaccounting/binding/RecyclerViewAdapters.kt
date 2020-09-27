package cf.feuerkrieg.homeaccounting.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cf.feuerkrieg.homeaccounting.adapters.MultipleChoiceAdapter

@BindingAdapter(value = ["singleChoiceEntries", "selectedItems"], requireAll = true)
fun <T> setSingleChoiceEntries(recyclerView: RecyclerView,
                               entries: List<T>?,
                                selectedItems: MutableList<T>?) {

    if(entries == null || selectedItems == null)
        return

    val adapter = MultipleChoiceAdapter<T>(recyclerView.context, entries, selectedItems)

    recyclerView.setHasFixedSize(true)
    recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    recyclerView.adapter = adapter
}

