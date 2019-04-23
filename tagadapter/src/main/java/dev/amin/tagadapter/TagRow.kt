package dev.amin.tagadapter

import kotlin.math.round

class TagRow {

    var freeSpans = 20

    val tagList = mutableListOf<Tag>()
    val spanList = mutableListOf<Int>()

    fun addTag(spanRequired: Float, tag: Tag) : Boolean {

        if (spanRequired < freeSpans)
            if (tagList.add(tag)) {

                val spanRequiredInt = round(spanRequired).toInt()

                spanList.add(spanRequiredInt)
                freeSpans -= spanRequiredInt

                return true
            }

        return false
    }
}