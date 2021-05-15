package com.applab.app_sqlite_lucky

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.applab.app_sqlite_lucky.db.DBHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val dbHelper = DBHelper(this)
    lateinit var recyclerAdapterLucky: RecyclerAdapterLucky
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        init()
        recyclerAdapterLucky.submitList(dbHelper.readOdds())
    }

    private fun init() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            recyclerAdapterLucky = RecyclerAdapterLucky()
            adapter = recyclerAdapterLucky
        }

    }
}