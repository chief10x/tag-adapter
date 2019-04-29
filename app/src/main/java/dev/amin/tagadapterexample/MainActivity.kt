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
            adapter = TagAdapter(tagList)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }

    private val tagList: MutableList<Tag>
        get() {
            return mutableListOf(
                Tag("Exercise"),
                Tag("Be Cool"),
                Tag("Floss"),
                Tag("Read the Sign"),
                Tag("Meditation"),
                Tag("Go Crazy"),
                Tag("Drink Water"),
                Tag("Be Cool in an awesome way"),
                Tag("No Alcohol"),
                Tag("Code like Crazy"),
                Tag("Zero Life"),
                Tag("Drunk in Funeral"),
                Tag("Small"),
                Tag("Not so Small"),
                Tag("Java"),
                Tag("Android"),
                Tag("Proud looser :D"),
                Tag("No Burgers!"),
                Tag("Play Guitar"),
                Tag("Clap the Article"),
                Tag("Walk"),
                Tag("Medium is Awesome"),
                Tag("Kotlin"),
                Tag("Dream"),
                Tag("Freedom"),
                Tag("Less Sugar"),
                Tag("Discipline"),
                Tag("No to Drugs :D"),
                Tag("Avengers"),
                Tag("Margarita"),
                Tag("Candies"),
                Tag("Listen to Opeth")
            )
        }
}
