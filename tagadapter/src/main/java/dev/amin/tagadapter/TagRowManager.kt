package dev.amin.tagadapter

class TagRowManager {

    val rowList = mutableListOf<TagRow>()
        .apply {
            add(TagRow())
        }

    fun add(spanRequired: Float, tag: Tag) {

        for (i in 0..rowList.size) {

            val tagRow = rowList[i]

            // Id the title was added and was fit to the list in dreamTitleRow
            if (tagRow.addTag(spanRequired, tag)) {
                break
            }

            // If the model did not fit in any of current cells
            if (i == rowList.lastIndex)
                rowList.add(TagRow())
        }
    }

    fun getSortedSpans(): MutableList<Int> {

        val spans = mutableListOf<Int>()

        rowList.forEach {
            spans.addAll(it.spanList)
        }

        return spans
    }

    fun getSortedTags(): MutableList<Tag> {

        val tags = mutableListOf<Tag>()

        rowList.forEach {

            tags.addAll(it.tagList)
        }

        return tags
    }
}