package com.applab.app_sqlite_lucky

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.applab.app_sqlite_lucky.db.DBHelper

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper.writableDatabase
    }
}