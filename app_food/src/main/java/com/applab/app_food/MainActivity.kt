package com.applab.app_food

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        val food = Food("m41370", "地中海辣鷄黑堡", 100, true, true)
        Toast.makeText(context, food.toString(), Toast.LENGTH_SHORT).show()



    }
}