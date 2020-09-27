package cf.feuerkrieg.homeaccounting.binding

import android.view.View
import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout

@BindingAdapter("error")
fun setError(textInputLayout: TextInputLayout, error: String) {
    textInputLayout.error = error
}


@BindingAdapter("visibility")
fun setVisibility(view: View, visibility: Int) {
    if(visibility == 1) {
        view.visibility = View.VISIBLE
    }
    else {
        view.visibility = View.GONE
    }
}


@BindingAdapter("visibility")
fun setVisibility(view: View, isVisible: Boolean) {
    if(isVisible) {
        view.visibility = View.VISIBLE
    }
    else {
        view.visibility = View.GONE
    }
}