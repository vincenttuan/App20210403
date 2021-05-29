package com.study.app_lifecycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MainActivity", "onCreate");

        btn_button.setOnClickListener {
            number++;
            et_text.setText(number.toString())
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MainActivity", "onStart");
    }

    override fun onResume() {
        super.onResume()
        Log.d("MainActivity", "onResume");
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("MainActivity", "onRestart");
    }

    override fun onPause() {
        super.onPause()
        Log.d("MainActivity", "onPause");
    }

    override fun onStop() {
        super.onStop()
        Log.d("MainActivity", "onStop");
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MainActivity", "onDestroy");
    }
}