package com.applab.app_exchange

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
    }

    fun onClick(view: View) {
        val intent = Intent(context, ExchangeActivity::class.java)
        // 將 input_number 所輸入的資料傳送到下一個 Activity (也就是 ResultActivity)
        intent.putExtra("amount", input_amount.text.toString().toInt())
        startActivity(intent)
    }
}