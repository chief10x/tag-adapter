package dev.amin.tagadapterexample

import dev.amin.tagadapter.Taggable

data class MyTaggableClass(val name: String, val subName: String? = null) : Taggable {
    override val tag: String
        get() = if (!subName.isNullOrEmpty())"$name $subName" else name
}