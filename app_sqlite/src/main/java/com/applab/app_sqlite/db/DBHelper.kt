package com.applab.app_sqlite.db

// 撰寫與資料庫建立,升級,銷毀與CRUD相關程序
class DBHelper {
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
}