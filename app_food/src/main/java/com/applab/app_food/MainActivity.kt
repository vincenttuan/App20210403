package com.applab.app_food

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.title.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

//        val food = Food("m41370", "地中海辣鷄黑堡", 100, true, false)
//
//        val imageId = resources.getIdentifier(food.idName, "drawable", packageName)
//        image_food.setImageResource(imageId)
//
//        text_name.text = food.name
//        text_price.text = food.price.toString()
//        if(food.spicy) {
//            image_spicy.setImageResource(R.drawable.isspicy)
//        } else {
//            image_spicy.setImageResource(android.R.color.transparent)
//        }
//        if(food.new) {
//            image_new.setImageResource(R.drawable.isnew)
//        } else {
//            image_new.setImageResource(android.R.color.transparent)
//        }

        service()
    }

    private fun service() {
        // 1. 抓取 assets 資料夾下面的 foods.json
        var json = assets.open("foods.json").bufferedReader().use { it.readText() }
        Log.d("MainActivity", json)
        // 2. json 字串轉成 foods 陣列(List)
        var foods = Gson().fromJson(json, Array<Food>::class.java).toList()
        Log.d("MainActivity", foods.toString())
        // 3. 建立適配器 adapter 給 gird_view 使用

    }

}