package com.applab.app_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var json = assets.open("csvjson.json").bufferedReader().use { it.readText() }
        // 將 json 字串轉 List<Map<String, Object>>
        val listType: Type = object : TypeToken<List<Map<String?, Object?>?>?>() {}.type
        val list: List<Map<String, Object>> = Gson().fromJson(json, listType)
        Log.d("MainActivity", list.toString())
    }
}