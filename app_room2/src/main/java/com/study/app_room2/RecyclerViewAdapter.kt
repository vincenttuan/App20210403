package com.study.app_room2

import android.view.View
import com.study.app_room.db.User
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.recyclerview_row.view.*

class RecyclerViewAdapter {
    private var users = ArrayList<User>()
    fun setUsers(users: ArrayList<User>) {
        this.users = users
    }

    // 專屬的 onClick 監聽介面
    interface RowOnClickListener {
        fun onItemClickListener(user: User)
        fun onDeleteUserClickListener(user: User)
    }

    // 建立 ViewHolder, 就是我們儲存 View 參考的地方
    // 或者是可以把它當成是一個儲存 View class 的地方
    class MyViewHolder(view: View, val listener: RowOnClickListener) {
        val tvUid = view.tv_uid
        val tvName = view.tv_name
        val tvAge = view.tv_age
        val cbWorking = view.cb_working
        val btnDelete = view.btn_delete
        fun bind(user: User) {
            tvUid.text = user.uid.toString()
            tvName.text = user.name
            tvAge.text = user.age.toString()
            cbWorking.isChecked = user.working
            btnDelete.setOnClickListener {
                listener.onDeleteUserClickListener(user)
            }
        }
    }
}