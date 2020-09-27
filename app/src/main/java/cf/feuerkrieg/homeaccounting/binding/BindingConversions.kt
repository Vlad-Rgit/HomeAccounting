package cf.feuerkrieg.homeaccounting.binding

import androidx.databinding.BindingConversion

@BindingConversion
fun convertIntToByte(int: Int): Byte
    = int.toByte()

@BindingConversion
fun convertByteToInt(byte: Byte): Int
    = byte.toInt()