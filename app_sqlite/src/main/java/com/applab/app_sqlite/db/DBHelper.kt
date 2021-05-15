package com.applab.app_sqlite.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

// 撰寫與資料庫建立,升級,銷毀與CRUD相關程序
class DBHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    // SingleTon (companion object相當於 Java 的 static inner class 靜態內部類別)
    companion object {
        val DATABASE_VERSION = 1 // 資料庫版本
        val DATABASE_NAME = "MyDB.db" // 資料庫名稱

        // 建立資料表 Student 的 SQL 語句
        val SQL_CREATE_STUDENT = "" +
                "CREATE TABLE Student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "score INTEGER," +
                "ct BIGINT)"
        // 刪除資料表 Student 的 SQL 語句
        val SQL_DELETE_STUDENT = "DROP TABLE IF EXISTS Student"

    }

    // 預設資料表的建立
    override fun onCreate(db: SQLiteDatabase?) {
        // db 指的是 MyDB.db 資料庫容器
        db?.execSQL(SQL_CREATE_STUDENT)
    }

    // 資料庫升級
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(SQL_DELETE_STUDENT)
        onCreate(db)
    }

}