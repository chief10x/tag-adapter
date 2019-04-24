package dev.amin.tagadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

    // Called when the MeasureHelper is done measuring.
    private fun update() {

        recyclerView ?: return

        recyclerView?.apply {

            visibility = View.VISIBLE
            layoutManager = MultipleSpanGridLayoutManager(context, 20)
        }
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView.apply {
            visibility = View.INVISIBLE
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.row_tag, parent, false))

    override fun getItemCount() = tagList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {

        holder.setData(tagList[position])

        if (measureHelper.shouldMeasure())
            measureHelper.measure(holder, position)
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setData(tag: Tag) {

            itemView.rowTitle.layoutParams.height = LinearLayout.LayoutParams.WRAP_CONTENT
            itemView.rowTitle.text = tag.title
        }
    }
}