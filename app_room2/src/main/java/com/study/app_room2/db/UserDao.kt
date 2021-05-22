package com.study.app_room.db

import androidx.room.*

@Dao // Data access object
interface UserDao {
    // 查詢單筆
    @Query("SELECT * FROM User WHERE uid = :uid")
    fun getUser(uid: Int): User

    // 查詢所有 user
    @Query("SELECT * FROM User")
    fun getAllUsers(): List<User>

    // 新增(含多筆批次)
    @Insert
    fun insert(vararg user: User)

    // 修改
    @Update
    fun update(user: User)

    // 刪除
    @Delete
    fun delete(user: User)

    // 刪除
    @Query("DELETE FROM User WHERE uid = :uid")
    fun delete(uid: Int)
}