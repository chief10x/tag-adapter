package dev.amin.tagadapterexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import dev.amin.tagadapter.OnTaggableClickListener
import dev.amin.tagadapter.Taggable
import dev.amin.tagadapter.TagAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.button1).setOnClickListener {
            recyclerView.apply {
                val tagAdapter = TagAdapter(tagList, R.layout.view_item_tag)
                tagAdapter.onTaggableClickListener = object : OnTaggableClickListener {
                    override fun onTaggableClick(taggable: Taggable) {
                        Toast.makeText(this@MainActivity, "Clicked on: ${taggable.tag}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                adapter = tagAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

            }
        }

        findViewById<View>(R.id.button2).setOnClickListener {
            recyclerView.apply {
                val tagAdapter = TagAdapter(tagList2, R.layout.view_item_tag)
                tagAdapter.onTaggableClickListener = object : OnTaggableClickListener {
                    override fun onTaggableClick(taggable: Taggable) {
                        Toast.makeText(this@MainActivity, "Clicked on: ${taggable.tag}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                adapter = tagAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)

            }
        }
    }

    private val tagList: MutableList<Taggable>
        get() {
            return mutableListOf(
                MyTaggableClass("Exercise"),
                MyTaggableClass("Be Cool"),
                MyTaggableClass("Floss"),
                MyTaggableClass("Read the Sign"),
                MyTaggableClass("Meditation"),
                MyTaggableClass("Be Cool in an awesome way"),
                MyTaggableClass("Go Crazy"),
                MyTaggableClass("Drink Water"),
                MyTaggableClass("Tag Team"),
                MyTaggableClass("No Alcohol"),
                MyTaggableClass("Code like Crazy"),
                MyTaggableClass("Zombies?"),
                MyTaggableClass("Zero Life"),
                MyTaggableClass("Just Don't do it"),
                MyTaggableClass("Drunk in Funeral"),
                MyTaggableClass("Listen to Opeth"),
                MyTaggableClass("Small"),
                MyTaggableClass("Not so Small"),
                MyTaggableClass("Java"),
                MyTaggableClass("Did anyone said Zombies?"),
                MyTaggableClass("Android"),
                MyTaggableClass("Proud looser :D"),
                MyTaggableClass("Tale of two taggies"),
                MyTaggableClass("No Pain no Tag"),
                MyTaggableClass("Code for Food"),
                MyTaggableClass("Bar Blatta"),
                MyTaggableClass("No Burgers!"),
                MyTaggableClass("Play Guitar"),
                MyTaggableClass("Clap the Article"),
                MyTaggableClass("Walk"),
                MyTaggableClass("Medium is Awesome"),
                MyTaggableClass("Kotlin"),
                MyTaggableClass("Dream"),
                MyTaggableClass("Freedom"),
                MyTaggableClass("Less Sugar"),
                MyTaggableClass("The longer the Tag the longer the Cell"),
                MyTaggableClass("Discipline"),
                MyTaggableClass("No to Drugs :D"),
                MyTaggableClass("Avengers"),
                MyTaggableClass("Run for your Life"),
                MyTaggableClass("Margarita"),
                MyTaggableClass("Candies")
            )
        }
    private val tagList2: MutableList<Taggable>
        get() {
            return mutableListOf(
                MyTaggableClass("Pop"),
                MyTaggableClass("Be Cool"),
                MyTaggableClass("Floss"),
                MyTaggableClass("Read the Sign")
            )
        }
}
