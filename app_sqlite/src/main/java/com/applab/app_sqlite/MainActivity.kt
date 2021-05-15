package com.applab.app_sqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.applab.app_sqlite.db.DBHelper

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        title = dbHelper.databaseName
        dbHelper.writableDatabase
    }
}