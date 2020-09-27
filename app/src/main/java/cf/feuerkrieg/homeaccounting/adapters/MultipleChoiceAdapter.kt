package cf.feuerkrieg.homeaccounting.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckedTextView
import androidx.recyclerview.widget.RecyclerView
import cf.feuerkrieg.homeaccounting.R

class MultipleChoiceAdapter<T>(
    private val context: Context,
    private val items: List<T>,
    val selectedItems: MutableList<T>)
    : RecyclerView.Adapter<MultipleChoiceAdapter.ViewHolder<T>>() {

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {

        val view = inflater.inflate(
            R.layout.multiple_choice_layout, parent, false)

        return ViewHolder<T>(view)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {

        val item = items[position]

        holder.performBind(item, selectedItems.contains(item))

        holder.setOnClickListener {
            clickedItem, isChecked ->

            if(isChecked)
                selectedItems.add(clickedItem)
            else
                selectedItems.remove(clickedItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder<T>(view: View)
        : RecyclerView.ViewHolder(view) {

        private var currentItem: T? = null

        private val ctv = view.findViewById<CheckedTextView>(
            R.id.ctv_multiple_choice)

        fun performBind(item: T, isChecked: Boolean) {
            currentItem = item
            ctv.text = item.toString()
            ctv.isChecked = isChecked
        }

        fun setOnClickListener(listener: (item: T, isChecked: Boolean) -> Unit) {

            itemView.setOnClickListener {
                ctv.isChecked = !ctv.isChecked
                listener.invoke(currentItem!!, ctv.isChecked)
            }
        }
    }
}