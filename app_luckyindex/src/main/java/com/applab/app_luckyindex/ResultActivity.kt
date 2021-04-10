package com.applab.app_luckyindex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import java.util.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)
        // 接收 MainActivity 所傳來的 number 資料
        val number = intent.getStringExtra("number").toString().toInt()
        // 取得 Lucky Index 的資料
        val luckyIndex = getLuckyIndex(number)
        // 將 luckyIndex 放到 View元件(text_result)中
        text_result.text = luckyIndex.toString()
        // 將現在時間放到 View元件(text_time)中
        text_time.text = Date().toString()
    }
}