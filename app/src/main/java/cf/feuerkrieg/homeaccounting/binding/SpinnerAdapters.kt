package cf.feuerkrieg.homeaccounting.binding

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import cf.feuerkrieg.homeaccounting.R
import cf.feuerkrieg.homeaccounting.interfaces.SpinnerSelectionChangedListener


@BindingAdapter("items")
fun <T> setItems(spinner: Spinner, items: List<T>?) {

    if(items == null)
        return

    var selectedIndex: Int = 0

    if(spinner.adapter != null)
        selectedIndex = spinner.selectedItemPosition


    val adapter = ArrayAdapter<T>(spinner.context,
        R.layout.simple_spinner_item, items)

    adapter.setDropDownViewResource(
        R.layout.simple_spinner_dropdown_item)

    spinner.adapter = adapter

    if(selectedIndex < items.size)
        spinner.setSelection(selectedIndex)
}


//For referencing string-array resources
@BindingAdapter("items")
fun setItems(spinner: Spinner, items: Array<String>?) {

    if(items == null)
        return

    val adapter = ArrayAdapter<String>(spinner.context,
        R.layout.simple_spinner_item, items)
    adapter.setDropDownViewResource(
        R.layout.simple_spinner_dropdown_item)

    spinner.adapter = adapter
}

@BindingAdapter("selectedIndex")
fun setSelectedIndex(spinner: Spinner, index: Int) {
    spinner.setSelection(index)
}

@BindingAdapter("selectedItem")
fun <T> setSelectedItem(spinner: Spinner, item: T?) {

    val adapter = spinner.adapter
    var index = 0

    if(item != null) {
        for (i in 0 until adapter.count) {
            if (adapter.getItem(i) == item) {
                index = i
                break
            }
        }
    }

    spinner.setSelection(index)
}

@BindingAdapter(value =
    ["selectionChanged",
    "selectionChangedAttr",
    "selectedIndexChangedAttr"],
    requireAll = false)

fun setSelectionChanged(spinner: Spinner,
                        selectionChanged: SpinnerSelectionChangedListener?,
                        selectionChangedListener: InverseBindingListener?,
                        selectedIndexChangedListener: InverseBindingListener?)
{
    spinner.onItemSelectedListener = object
        : AdapterView.OnItemSelectedListener {

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
            selectedIndexChangedListener?.onChange()
            selectionChangedListener?.onChange()
            selectionChanged?.selectionIndexChanged(position)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }
    }
}

@InverseBindingAdapter(attribute = "selectedItem",
                        event = "selectionChangedAttr")
fun <T> getSelectedItem(spinner: Spinner) : T {
    return spinner.selectedItem as T
}


@InverseBindingAdapter(attribute = "selectedIndex",
                        event = "selectedIndexChangedAttr")
fun getSelectedIndex(spinner: Spinner): Int {
    return spinner.selectedItemPosition
}