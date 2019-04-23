package dev.amin.tagadapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import dev.amin.tagadapter.Tag
import dev.amin.tagadapter.TagAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.apply {
            adapter = TagAdapter(this@MainActivity, tagList)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private val tagList: MutableList<Tag>
        get() {
            return mutableListOf(
                Tag("Exercise"),
                Tag("Eat Healthy"),
                Tag("Floss"),
                Tag("Read the Book"),
                Tag("Meditation"),
                Tag("Go Crazy"),
                Tag("Drink Water"),
                Tag("No Nicotine"),
                Tag("No Alchohol"),
                Tag("aaaaa"),
                Tag("abskdgdga"),
                Tag("ash"),
                Tag("auhf aidhfhusfbj")
            )
        }
}
