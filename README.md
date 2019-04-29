# Tag Adapter
## Tag adapter is an android custom recycler view adapter which allows you to create a multiple span adapter to show views like tags.


![ScreenShut](https://github.com/amindevv/tag-adapter/blob/master/screenshuts/tag-adapter-screen-shhut.png)

### Usage

Just TagAdapter as the adapter of the recyclerView.
```Kotlin
recyclerView.apply {

            // tagList is just a mutableList of Tag
            adapter = TagAdapter(tagList)
            
            // LayoutManager does n't really matter since the library will override it
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
```

For the data set you should use the Tag object.

```Kotlin
val tagList : MutableList<Tag>
        get() {
            return mutableListOf(
                Tag("Exercise"),
                Tag("Be Cool"),
                Tag("Floss"),
                Tag("Read the Sign"),
                Tag("Meditation"),
                Tag("Be Cool in an awesome way"),
                Tag("Go Crazy"),
                Tag("Drink Water")
            )
        }
```
