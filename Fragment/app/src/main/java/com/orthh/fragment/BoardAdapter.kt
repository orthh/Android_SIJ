package com.orthh.fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.RecyclerView
import com.orthh.fragment.vo.BoardVO
import com.bumptech.glide.Glide

class BoardAdapter(var datas: ArrayList<BoardVO>, var context: Context) :RecyclerView.Adapter<BoardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.board_item,parent, false)
        )
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        var board =datas[position]

        holder.tv_title.text = board.title
        holder.tv_writer.text = board.writer
        holder.tv_likes.text = board.likeCnt.toString()
        holder.iv_heart.setOnClickListener {

        }
    }


}