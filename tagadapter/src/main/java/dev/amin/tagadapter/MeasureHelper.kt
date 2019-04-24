package dev.amin.tagadapter

import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_tag.view.*

class MeasureHelper(
    private val adapter: TagAdapter,
    private val count: Int
) {

    // Contains the span count [position] => [spanCount]
    private val spanMap = hashMapOf<Int, Float>()

    private var baseCell: Float = 0f

    fun measureBaseCell(width: Int) {
        baseCell = (width / 20).toFloat()
    }

    fun shouldMeasure() = spanCount.size != count

    private fun cellMeasured() {

        if (!adapter.measuringDone && !shouldMeasure())
            adapter.measuringDone = true
    }

    fun measure(holder: TagAdapter.Holder, position: Int) {

        // Get the ItemView and minimize it's height as small as possible
        val itemView = holder.itemView.apply {
            layoutParams.height = 1
        }

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                val marginTotal =
                    (itemView.rowTitle.layoutParams as ViewGroup.MarginLayoutParams).marginStart * 2

                // calculate the required span here and add it to spanCount.
                // Also Create a reference to rowManager here and add the span

                spanCount[position] =
                    ((itemView.rowTitle.width + marginTotal) / baseCell)

                cellMeasured()

                itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }

        itemView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }
}