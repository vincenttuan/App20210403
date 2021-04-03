package com.applab.mybmiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var h_view: EditText
    lateinit var w_view: EditText
    lateinit var result_view: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        h_view = findViewById(R.id.tf_height)
        w_view = findViewById(R.id.tf_weight)
        result_view = findViewById(R.id.result_view)
    }

    // 使用者按下 btn_calc 所要做的事
    fun calc(view: View) {
        val h = h_view.text.toString().toDouble()
        val w = w_view.text.toString().toDouble()
        val bmi = w / Math.pow(h/100, 2.0)
        // 利用 Toast 顯示 bmi 的計算值
        Toast.makeText(this, "%.2f".format(bmi), Toast.LENGTH_SHORT).show()
        result_view.text = "%.2f".format(bmi)

    }
}