package dev.amin.tagadapter

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class MultipleSpanGridLayoutManager(context: Context, spanCount: Int, spanList: MutableList<Int>) :
    GridLayoutManager(context, spanCount) {

    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (position < spanList.size) {
                    spanList[position]
                } else {
                    1
                }
            }
        }
    }
}