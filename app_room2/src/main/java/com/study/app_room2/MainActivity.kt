package com.study.app_room2

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        db = Room.databaseBuilder(context, UserDatabase::class.java, "mydb")
            //.createFromAsset("databases/user.db")
            .build()

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

        // Save/Update
        btn_submit.setOnClickListener {
            if(!checkUserForm()) return@setOnClickListener

            val name = et_name.text.toString()
            val age = et_age.text.toString().toInt()
            val working = cb_working.isChecked

            GlobalScope.launch {
                if(btn_submit.text.equals("Save")) { // 新增
                    val user = User(name, age, working)
                    db.userDao().insert(user)
                    reload()
                } else { // 修改
                    // 從 et_name 的 tag 中取出 uid
                    val uid = et_name.getTag().toString().toInt()
                    val user = db.userDao().getUser(uid)
                    if(user != null) {
                        user.name = name
                        user.age = age
                        user.working = working
                        db.userDao().update(user)
                        reload()
                    }
                }
            }

        }

    }

    fun checkUserForm(): Boolean {
        if(et_name.text.toString().isEmpty()) {
            return false
        }
        if(et_age.text.toString().isEmpty()) {
            return false
        }
        return true
    }

    fun reload() {
        GlobalScope.launch {
            var users = db.userDao().getAllUsers()
            recyclerViewAdapter.setUsers(users as ArrayList<User>)
            runOnUiThread {
                recyclerViewAdapter.notifyDataSetChanged()
                clear()
            }
        }
    }

    fun clear() {
        et_name.setText("")
        et_age.setText("")
        cb_working.isChecked = false
        btn_submit.setText("Save")
    }

    override fun onItemClickListener(user: User) {
        Toast.makeText(context, "Update: " + user.toString(), Toast.LENGTH_SHORT).show()
        et_name.setText(user.name)
        et_age.setText(user.age.toString())
        cb_working.isChecked = user.working
        // 埋一個 tag 資料給修改用
        et_name.setTag(user.uid)
        // 修改 button 上面的字
        btn_submit.setText("Update")
    }

    override fun onDeleteUserClickListener(user: User) {
        val listener = DialogInterface.OnClickListener { dialog, which ->
            when(which) {
                DialogInterface.BUTTON_POSITIVE -> {
                    GlobalScope.launch {
                        val delUser = db.userDao().getUser(user.uid!!.toInt())
                        if(delUser != null) {
                            db.userDao().delete(delUser.uid!!.toInt())
                        }
                        reload()
                    }
                }
                DialogInterface.BUTTON_NEGATIVE -> {

                }
            }
        }
        AlertDialog.Builder(this)
            .setTitle("是否要刪除?")
            .setMessage(user.toString())
            .setPositiveButton("Delete", listener)
            .setNegativeButton("Cancel", null)
            .show()
    }
}