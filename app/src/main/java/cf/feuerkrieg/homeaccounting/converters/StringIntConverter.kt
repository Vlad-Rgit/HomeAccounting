package cf.feuerkrieg.homeaccounting.converters

import androidx.databinding.InverseMethod

// String / Int conversion for data binding

fun convert(int: Int): String {
    return int.toString()
}

@InverseMethod("convert")
fun convert(string: String): Int {
    return string.toInt()
}