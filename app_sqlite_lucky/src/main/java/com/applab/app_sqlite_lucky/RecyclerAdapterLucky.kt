package com.applab.app_sqlite_lucky

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.applab.app_sqlite_lucky.models.Lucky
import kotlinx.android.synthetic.main.row.view.*
import java.util.ArrayList

class RecyclerAdapterLucky : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val items: List<Lucky> = ArrayList()

    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        private val id: TextView = itemView.view_id
        private val color: TextView = itemView.view_color
        private val num: TextView = itemView.view_num

        fun bind(lucky: Lucky) {
            id.setText(lucky.id)
            color.setText(lucky.color)
            num.setText(lucky.num)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = items[position]
        when(holder) {
            is ViewHolder -> {
                holder.bind(currentItem)
            }
        }
    }

}