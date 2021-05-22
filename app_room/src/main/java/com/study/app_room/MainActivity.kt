package com.study.app_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import com.study.app_room.db.User
import com.study.app_room.db.UserDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java,
            "mydb"
        ).build()

        val user1 = User("John", 18, true)
        val user2 = User("Mary", 19, false)

        GlobalScope.launch {
            db.userDao().insert(user1, user2)
        }

    }
}