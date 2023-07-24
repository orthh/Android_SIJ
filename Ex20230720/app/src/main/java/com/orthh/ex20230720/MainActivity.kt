package com.orthh.ex20230720

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    // listView 의 업그레이드 버전
    // RecyclerView를 만들어 보자!
    // Custom RecyclerView
    // 원리
    // Data의 개수만큼 Template를 복사하여 RecyclerView안에 배치!
    // 리액트의 map 이랑 비슷한듯
    // => who? Adapter가 함!

    // 1. Data (VO, ArrayList)
    // 2. Template(.xml)
    // 3. Adapter
    // - ViewHolder도 만들어야함

    // 이미지 -> int(주소), 이름 -> String, 메시지 -> String, 시간 : String --> VO
    // VO 담은 ArrayList

    lateinit var rv: RecyclerView
    lateinit var btn_send : Button
    lateinit var edt_msg: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv_result)
        btn_send = findViewById(R.id.btn_send)
        edt_msg = findViewById(R.id.edt_msg)
        var data = ArrayList<KakaoVO>()


        // add 함수 사용하여 메시지 5개 저장~
        data.add(KakaoVO(R.drawable.img1, "피카츄", "피카피카", "4:17"))
        data.add(KakaoVO(R.drawable.img2, "파이리", "파이파일파이리", "4:19"))
        data.add(KakaoVO(R.drawable.img3, "꼬부기", "꼬북꼬북", "4:21"))
        data.add(KakaoVO(R.drawable.img4, "이상해씨", "이상이상해", "4:23"))
        data.add(KakaoVO(R.drawable.img5, "라이츄", "라이츄츄츄이츄", "4:33"))
        
        var adapter: KakaoAdapter = KakaoAdapter(applicationContext, R.layout.template, data)

        // layoutManager 세팅
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapter

        btn_send.setOnClickListener {
            data.add(KakaoVO(R.drawable.img1, "펑이", edt_msg.text.toString(), "오후 16:38"))
            // adapter 새로고침
            adapter.notifyDataSetChanged()
            // 스크롤 옮기기
            rv.smoothScrollToPosition(data.size - 1)
            // EditText 비우기
            edt_msg.text.clear()

        }

    }
}