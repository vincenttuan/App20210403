package com.applab.app_recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var json = assets.open("csvjson.json").bufferedReader().use { it.readText() }
        Log.d("MainActivity", json)
    }
}