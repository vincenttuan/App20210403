package com.applab.app_food

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        val food = Food("m41370", "地中海辣鷄黑堡", 100, true, false)
        Toast.makeText(context, food.toString(), Toast.LENGTH_SHORT).show()

        val imageId = resources.getIdentifier(food.idName, "drawable", packageName)
        image_food.setImageResource(imageId)

        text_name.text = food.name
        text_price.text = food.price.toString()
        if(food.spicy) {
            image_spicy.setImageResource(R.drawable.isspicy)
        } else {
            image_spicy.setImageResource(android.R.color.transparent)
        }
        if(food.new) {
            image_new.setImageResource(R.drawable.isnew)
        } else {
            image_new.setImageResource(android.R.color.transparent)
        }

    }
}