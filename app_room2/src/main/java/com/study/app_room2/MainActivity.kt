package com.study.app_room2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.DividerItemDecoration.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.study.app_room2.db.User
import com.study.app_room2.db.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowOnClickListener {
    lateinit var db: UserDatabase
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        db = Room.databaseBuilder(context, UserDatabase::class.java, "mydb").build()

        GlobalScope.launch {
            var users = db.userDao().getAllUsers()
            if (users.size == 0) {
                db.userDao().insert(
                    User("John", 18, true),
                    User("Mary", 19, false)
                )
                users = db.userDao().getAllUsers()
            }
            // 配置 recyclerView
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
                adapter = recyclerViewAdapter
                // 分隔線的設置
                val divider = DividerItemDecoration(context, VERTICAL)
                addItemDecoration(divider)
            }
            recyclerViewAdapter.setUsers(users as ArrayList<User>)
            recyclerViewAdapter.notifyDataSetChanged()
        }

        // Save
        btn_submit.setOnClickListener {
            val name = et_name.text.toString()
            val age = et_age.text.toString().toInt()
            val working = cb_working.isChecked
            if(btn_submit.text.equals("Save")) {
                val user = User(name, age, working)
                db.userDao().insert(user)
                reload()
            }
        }

    }

    fun reload() {
        GlobalScope.launch {
            var users = db.userDao().getAllUsers()
            recyclerViewAdapter.setUsers(users as ArrayList<User>)
            runOnUiThread {
                recyclerViewAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemClickListener(user: User) {
        TODO("Not yet implemented")
    }

    override fun onDeleteUserClickListener(user: User) {
        TODO("Not yet implemented")
    }
}