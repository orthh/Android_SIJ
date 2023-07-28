package com.orthh.fragment

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class BoardViewHolder(var itemView : View) : RecyclerView.ViewHolder(itemView) {
    lateinit var tv_title: TextView
    lateinit var tv_writer: TextView
    lateinit var tv_likes: TextView
    lateinit var iv_heart : ImageView

    init{ // component 초기화
        tv_title = itemView.findViewById(R.id.tv_title)
        tv_writer = itemView.findViewById(R.id.tv_writer)
        tv_likes = itemView.findViewById(R.id.tv_likes)
        iv_heart = itemView.findViewById(R.id.iv_heart)
    }
}