package cf.feuerkrieg.homeaccounting.extensions

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import cf.feuerkrieg.homeaccounting.adapters.ReplacableAdapter

@BindingAdapter("items")
fun <T> setItems(recyclerView: RecyclerView, items: Collection<T>?) {
    if(items != null) {
        val adapter = recyclerView.adapter as ReplacableAdapter<T>
        adapter.replaceAll(items)
    }
}