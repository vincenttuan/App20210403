package com.applab.app_sqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.applab.app_sqlite.db.DBHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    lateinit var context: Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        title = dbHelper.databaseName

        // 按下新增
        btn_create.setOnClickListener {
            // 新增紀錄
            create()
        }

    }

    // 新增紀錄
    fun create() {
        val name = et_name.text.toString()
        val score = et_score.text.toString()
        dbHelper.createStudent(name, score.toInt())
        clearFields()
    }

    // 清除欄位資料
    fun clearFields() {
        et_id.setText("")
        et_name.setText("")
        et_score.setText("")
    }
}