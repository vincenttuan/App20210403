package com.study.app_lifecycler2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_button.setOnClickListener {
            GlobalScope.launch {
                while (true) {
                    number++
                    runOnUiThread {
                        et_text.setText(number.toString())
                    }
                    Thread.sleep(1000)
                }
            }
        }



    }
}