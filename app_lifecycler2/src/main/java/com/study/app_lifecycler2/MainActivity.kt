package com.study.app_lifecycler2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    var number = 0
    var play = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState != null) {
            number = savedInstanceState.getInt("number", 0)
            play = savedInstanceState.getBoolean("play", false)
            if(play) {
                playGame()
            }
        }

        btn_button.setOnClickListener {
            play = !play
            playGame()
        }

    }

    fun playGame() {

        GlobalScope.launch {
            runOnUiThread {
                if(play) {
                    btn_button.setText("Stop")
                } else {
                    btn_button.setText("Start")
                }
            }
            while (play) {
                number++
                runOnUiThread {
                    et_text.setText(number.toString())
                }
                Thread.sleep(1000)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("number", number)
        outState.putBoolean("play", play)
        Log.d("MainActivity", number.toString() + " : " + play.toString())
        super.onSaveInstanceState(outState)
    }
}