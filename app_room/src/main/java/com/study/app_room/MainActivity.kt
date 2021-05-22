package com.study.app_room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.github.javafaker.Faker
import com.study.app_room.db.User
import com.study.app_room.db.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

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
            val users = db.userDao().getAllUsers()
            Log.d("MainActivity", users.toString())
            if(users.size == 0) {
                db.userDao().insert(user1, user2)
            }
        }

        btn_insert.setOnClickListener {
            val faker = Faker()
            val name = faker.name().firstName()
            val age = Random.nextInt(30)
            val working = Random.nextInt(2) == 0
            val user = User(name, age, working)
            GlobalScope.launch {
                db.userDao().insert(user)
            }
            Toast.makeText(applicationContext, "Insert", Toast.LENGTH_SHORT).show()
        }

        btn_query.setOnClickListener {
            GlobalScope.launch {
                val users = db.userDao().getAllUsers()
                runOnUiThread {
                    view_result.setText(users.toString())
                }
            }
        }

        btn_update.setOnClickListener {

            val faker = Faker()
            val name = faker.name().firstName()
            val age = Random.nextInt(30)
            val working = Random.nextInt(2) == 0

            GlobalScope.launch {
                val user = db.userDao().getUser(1)
                if(user != null) {
                    user.name = name
                    user.age = age
                    user.working = working
                }
                db.userDao().update(user)
            }
            Toast.makeText(applicationContext, "Update", Toast.LENGTH_SHORT).show()
        }

        btn_delete.setOnClickListener {
            val builder = AlertDialog.Builder(applicationContext)
            builder.setTitle("Delete")
            val editText = EditText(applicationContext)
            builder.setView(editText);
            builder.setPositiveButton("Delete") {
                dialog, which -> {
                    GlobalScope.launch {
                        val uid = editText.text.toString().toInt()
                        db.userDao().delete(uid)
                    }
                }
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }

    }
}