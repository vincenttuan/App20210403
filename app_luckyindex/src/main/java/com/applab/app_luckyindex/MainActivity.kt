package com.applab.app_luckyindex

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    val TAG = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }

    fun onClick(view: View) {
        Log.d(TAG, input_number.text.toString())
        val intent = Intent(context, ResultActivity::class.java)
        // 將 input_number 所輸入的資料傳送到下一個 Activity (也就是 ResultActivity)
        intent.putExtra("number", input_number.text.toString())
        startActivity(intent)
    }
}