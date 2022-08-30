package dev.amin.tagadapter

import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.TextView

class MeasureHelper(
    private val adapter: TagAdapter,
    private val count: Int
) {

    companion object {

        /***
         * Number of the total spans that the layout manager is allowed to draw.
         */
        const val SPAN_COUNT = 52
    }

    /***
     * Holds the count of measured views to know when measuring is finished.
     */
    private var measuredCount = 0

    /***
     * TagRowManager is responsible for adding and getting of the tags.
     */
    private val rowManager = TagRowManager()

    /***
     * baseCell is the value used for determining how much span will a holder require.
     */
    private var baseCell: Float = 0f

    fun measureBaseCell(width: Int) {
        baseCell = (width / SPAN_COUNT).toFloat()
    }

    /***
     * If measuring is finished or not.
     */
    fun shouldMeasure() = measuredCount != count

    fun getItems() = rowManager.getSortedTags()

    fun getSpans() = rowManager.getSortedSpans()

    /***
     * Calling this method every time a cell is measured and
     * if there is no more cell to measure it will notify the adapter
     * to update it self.
     */
    fun cellMeasured() {
        if (!adapter.measuringDone && !shouldMeasure()) {
            adapter.measuringDone = true
        }
    }

    fun measure(holder: TagAdapter.ItemViewHolder, tag: Taggable) {

        /* Get the ItemView and minimize it's height as small as possible
            to fit as many cells as it's possible in the screen. */
        with(holder.itemView as TextView) {
            layoutParams.height = 1

            val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {

                    // Remove the observer to avoid multiple callbacks.
                    viewTreeObserver.removeOnGlobalLayoutListener(this)

                    // Include also the horizontal margin of the layout.
                    val marginLayoutParams = layoutParams as ViewGroup.MarginLayoutParams
                    val marginTotal = marginLayoutParams.marginStart + marginLayoutParams.marginEnd
                    val paddingTotal = paddingStart/* + paddingEnd*/

                    // Required span for the holder in Float/
                    val span = (width + marginTotal /*+ paddingTotal*/) / baseCell

                    // Increase measured count.
                    measuredCount++

                    rowManager.add(span, tag)

                    cellMeasured()
                }
            }

            // Observe for the view
            viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
        }
    }
}