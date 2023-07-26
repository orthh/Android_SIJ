package com.orthh.ex20230726

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter

class LinkAdapter(var context: Context, var template : Int, var data: ArrayList<LinkVO>)
    : Adapter<LinkViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LinkViewHolder {
        var template_View : View = LayoutInflater.from(context).inflate(template, parent, false)
        var VH : LinkViewHolder = LinkViewHolder(template_View)
        return VH
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: LinkViewHolder, position: Int) {

        var tv_title : TextView = holder.tv_title
        var tv_url : TextView = holder.tv_url
        var btn_go : Button = holder.btn_go

        var LinkInfo : LinkVO = data.get(position)

        tv_title.text = LinkInfo.title
        tv_url.text = LinkInfo.link
        btn_go.setOnClickListener {
            var it = Intent(Intent.ACTION_VIEW, Uri.parse(data[position].link))
            // Activity가 아닌 곳에서 Intent를 실행시키고 싶다?
            // 새로운 테스크를 생성해야함!
            it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // MainActivity 화면 정보를 생성자의 context 변수로 전달받았음!
            context.startActivity(it!!)
        }

    }


}