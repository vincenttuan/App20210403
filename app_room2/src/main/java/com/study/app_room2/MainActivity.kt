package com.study.app_room2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.study.app_room.db.User
import com.study.app_room.db.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDatabase
    lateinit var recyclerViewAdapter: RecyclerViewAdapter
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        db = Room.databaseBuilder(context, UserDatabase::class.java, "mydb").build()

        GlobalScope.launch {
            val users = db.userDao().getAllUsers()
            if (users.size == 0) {
                db.userDao().insert(
                    User("John", 18, true),
                    User("Mary", 19, false)
                )
            }

        }
    }
}