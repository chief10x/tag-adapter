package dev.amin.tagadapter

import android.view.ViewGroup
import android.view.ViewTreeObserver
import kotlinx.android.synthetic.main.row_tag.view.*

class MeasureHelper(
    private val adapter: TagAdapter,
    private val count: Int
) {

    private val measureMap = hashMapOf<Int, Int>()

    init {

    }

    fun shouldMeasure() = measureMap.size != count

    private fun cellMeasured() {

        // I don't know why the HELL it's not working if I use the shouldMeasure!
        if (!adapter.measuringDone && measureMap.size == count)
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

                measureMap[position] = itemView.rowTitle.width + marginTotal

                cellMeasured()

                itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)
            }
        }

        itemView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }
}