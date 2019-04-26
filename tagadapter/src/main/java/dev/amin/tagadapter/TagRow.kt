package dev.amin.tagadapter

import kotlin.math.ceil

class TagRow {

    var freeSpans = 52

    val tagList = mutableListOf<Tag>()
    val spanList = mutableListOf<Int>()

    fun addTag(spanRequired: Float, tag: Tag) : Boolean {

        // calculate the view margins and reduce it from the span!! IMPORTANT

        if (spanRequired < freeSpans)
            if (tagList.add(tag)) {

                val spanRequiredInt = ceil(spanRequired).toInt()

                spanList.add(spanRequiredInt)
                freeSpans -= spanRequiredInt

                return true
            }

        return false
    }
}