package com.study.app_room

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.room.Room
import com.github.javafaker.Faker
import com.study.app_room.db.User
import com.study.app_room.db.UserDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.user_form.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    lateinit var db: UserDatabase
    lateinit var context: Context
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this
        db = Room.databaseBuilder(
            context,
            UserDatabase::class.java,
            "mydb"
        ).build()

        val user1 = User("John", 18, true)
        val user2 = User("Mary", 19, false)

        GlobalScope.launch {
            val users = db.userDao().getAllUsers()
            Log.d("MainActivity", users.toString())
            if (users.size == 0) {
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
            Toast.makeText(context, "Insert", Toast.LENGTH_SHORT).show()
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
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Update")
            val dialoglayout = layoutInflater.inflate(R.layout.user_form, null)
            builder.setView(dialoglayout);
            builder.setPositiveButton("Update") { dialog, which ->
                // dialoglayout ui 的資料
                val uid  = dialoglayout.findViewById<EditText>(R.id.et_uid).text.toString().toInt()
                val name = dialoglayout.findViewById<EditText>(R.id.et_name).text.toString()
                val age  = dialoglayout.findViewById<EditText>(R.id.et_age).text.toString().toInt()

                GlobalScope.launch {
                    val user = db.userDao().getUser(uid)
                    if (user != null) {
                        user.name = name
                        user.age = age
                    }
                    db.userDao().update(user)
                }
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()


//            val faker = Faker()
//            val name = faker.name().firstName()
//            val age = Random.nextInt(30)
//            val working = Random.nextInt(2) == 0
//
//            GlobalScope.launch {
//                val user = db.userDao().getUser(1)
//                if (user != null) {
//                    user.name = name
//                    user.age = age
//                    user.working = working
//                }
//                db.userDao().update(user)
//            }
//            Toast.makeText(context, "Update", Toast.LENGTH_SHORT).show()
        }

        btn_delete.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setTitle("Delete")
            val editText = EditText(context)
            builder.setView(editText);
            builder.setPositiveButton("Delete") { dialog, which ->
                val uid = editText.text.toString()
                Toast.makeText(context, uid, Toast.LENGTH_SHORT).show()
                GlobalScope.launch {
                    db.userDao().delete(uid.toInt())
                }
            }
            builder.setNegativeButton("Cancel", null)
            builder.show()
        }

    }

    val listener = DialogInterface.OnClickListener { dialog, which ->
        when (which) {
            DialogInterface.BUTTON_POSITIVE -> {

            }
            DialogInterface.BUTTON_NEGATIVE -> {


            }
        }
    }
}