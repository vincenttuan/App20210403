package com.applab.app_food

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item.view.*
import kotlinx.android.synthetic.main.title.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

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
        val adapter = object : ArrayAdapter<Food>(
            context,
            R.layout.item,
            R.id.text_name,
            foods) {

            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                // v 這裡指的就是 R.layout.item 所配置的物件
                val v = super.getView(position, convertView, parent)
                val food = getItem(position) // 得到 food 物件資料
                val textName = v.text_name
                val textPrice = v.text_price
                val imageFood = v.image_food
                val imageSpicy = v.image_spicy
                val imageNew = v.image_new
                val imageFoodId = resources.getIdentifier(food?.idName, "drawable", packageName)
                textName.text = food?.name
                textPrice.text = food?.price.toString()
                imageFood.setImageResource(imageFoodId)
                if(food!!.spicy) {
                    imageSpicy.setImageResource(R.drawable.isspicy)
                } else {
                    imageSpicy.setImageResource(android.R.color.transparent)
                }
                if(food!!.new) {
                    imageNew.setImageResource(R.drawable.isnew)
                } else {
                    imageNew.setImageResource(android.R.color.transparent)
                }
                return v
            }
        }
        // 4. 配置 adpater 給 gird_view
        grid_view.adapter = adapter

        // grid_view onItemClick 監聽
        grid_view.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                val food = parent?.getItemAtPosition(position) as Food
                //Toast.makeText(context, food.toString(), Toast.LENGTH_SHORT).show()
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

}