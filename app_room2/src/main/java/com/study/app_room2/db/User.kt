package com.study.app_room2.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "age") var age: Int,
    @ColumnInfo(name = "working") var working: Boolean
) {
    // 主鍵
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}