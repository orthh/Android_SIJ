package com.orthh.ex20230726

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class LinkViewHolder(var itemView : View) : ViewHolder(itemView){

    var tv_title : TextView
    var tv_url : TextView
    var btn_go :Button

    init{
        tv_title = itemView.findViewById(R.id.tv_title)
        tv_url = itemView.findViewById(R.id.tv_url)
        btn_go = itemView.findViewById(R.id.btn_go)
    }






}