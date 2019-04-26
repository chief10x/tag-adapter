package dev.amin.tagadapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_tag.view.*
import kotlin.properties.Delegates

class TagAdapter(
    private val context: Context,
    private var tagList: MutableList<Tag>
) : RecyclerView.Adapter<TagAdapter.Holder>() {

    private val measureHelper = MeasureHelper(this, tagList.size)

    var recyclerView: RecyclerView? = null

    var measuringDone by Delegates.observable(false) { _, _, newVal ->
        if (newVal)
            update()
    }

    private var ready = false

    // Called when the MeasureHelper is done measuring.
    private fun update() {

        recyclerView ?: return

        recyclerView?.apply {

            visibility = View.VISIBLE
            tagList = measureHelper.getItems()
            layoutManager = MultipleSpanGridLayoutManager(context, 52, measureHelper.getSpans())
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)

        this.recyclerView = recyclerView.apply {
            visibility = View.INVISIBLE

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {

                    recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                    // Measure the BaseCell on MeasureHelper and Start the Adapter
                    measureHelper.measureBaseCell(recyclerView.width)

                    ready = true
                    notifyDataSetChanged()
                }
            })
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.row_tag, parent, false))

    override fun getItemCount() = if (ready) tagList.size else 0

    override fun onBindViewHolder(holder: Holder, position: Int) {

        val tag = tagList[position]

        val shouldMeasure = measureHelper.shouldMeasure()

        holder.setData(tag, shouldMeasure)

        if (shouldMeasure)
            measureHelper.measure(holder, tag)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(tag: Tag, shouldMeasure: Boolean) {

            itemView.rowTitle.text = tag.title

            itemView.rowTitle.layoutParams.width = LinearLayout.LayoutParams.WRAP_CONTENT
            itemView.rowTitle.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT

            /* if the measuring is done set the width to fill the whole cell to avoid unexpected
            empty spaces between the cells */
            if (!shouldMeasure)
                itemView.rowTitle.layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
        }
    }
}