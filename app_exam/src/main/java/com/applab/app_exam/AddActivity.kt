package com.applab.app_exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_add.*

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

    }

    fun onClick(view: View) {
        val name = et_name.text.toString()
        val score = et_score.text.toString()
        // 傳送資料到 MainActivity
        intent.putExtra("name", name)
        intent.putExtra("score", score)
        setResult(101, intent)
        finish() // 將 ResultActivity 畫面關閉
    }
}
