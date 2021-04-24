package com.applab.app_circle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        btn_circle.setOnClickListener {
            val intent = Intent(context, CircleActivity::class.java)
            startActivity(intent)
        }

        btn_earth.setOnClickListener {
            val intent = Intent(context, EarthActivity::class.java)
            startActivity(intent)
        }
    }

    private val openResultActivityCustom = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        result -> {
            if(result.resultCode == 101) {

            } else if(result.resultCode == 201) {

            }
        }
    }


}