package com.applab.app_exam

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar;
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

// User data class 包含學生姓名, 考試分數
data class User(val name: String, val score: Int)

class MainActivity : AppCompatActivity() {
    lateinit var context: Context
    // 建立 users 資料集合
    // 其結果會放在 listview 物件中
    val users = mutableListOf(User("John", 100), User("Mary", 90))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        context = this

        // FAB
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            //        .setAction("Action", null).show()
            val intent = Intent(context, AddActivity::class.java)
            // 轉跳 Activity
            openResultActivityCustom.launch(intent)

        }

        // listView 的適配器
        val adapter = object : ArrayAdapter<User?>(
            context,
            android.R.layout.simple_list_item_2,
            android.R.id.text1,
            users as List<User?>) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val v = super.getView(position, convertView, parent)
                val name = v.findViewById<View>(android.R.id.text1) as TextView
                val score = v.findViewById<View>(android.R.id.text2) as TextView
                name.text = getItem(position)?.name
                score.text = getItem(position)?.score.toString()
                return v
            }
        }
        // listView 配置適配器
        list_view.adapter = adapter

    }

    private val openResultActivityCustom =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == 101) {

            }
        }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_exit -> true
            R.id.action_sum -> true
            R.id.action_avg -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}