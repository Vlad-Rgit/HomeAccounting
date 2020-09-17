package cf.feuerkrieg.homeaccounting.extensions

import android.animation.ValueAnimator
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("revealed")
fun setRevealed(view: View, revealed: Boolean?) {

    if(revealed == null) {
        view.visibility = View.GONE
        return
    }
    else {
        view.visibility = View.VISIBLE
    }



    val animator: ValueAnimator

    if(revealed)
        animator = ValueAnimator.ofInt(0, view.measuredHeight)
    else
        animator = ValueAnimator.ofInt(view.measuredHeight, 0)

    animator.addUpdateListener {
        val animatedValue = it.animatedValue as Int
        val layoutParams = view.layoutParams
        layoutParams.height = animatedValue
        view.layoutParams = layoutParams
    }



    /*animator.doOnEnd {
        if(revealed)
            view.visibility = View.VISIBLE
        else
            view.visibility = View.GONE
    }*/

    animator.start()
}