package cf.feuerkrieg.homeaccounting.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun setError(textInputLayout: TextInputLayout, error: String) {
    textInputLayout.error = error
}