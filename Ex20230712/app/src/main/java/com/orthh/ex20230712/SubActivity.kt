package com.orthh.ex20230712

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView

class SubActivity : AppCompatActivity() {

    lateinit var btn_tomain: Button
    lateinit var listview: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        btn_tomain = findViewById(R.id.btn_tomain)
        listview = findViewById(R.id.listview)

        // Intent로 전달된 데이터 꺼내서 버튼에 띄우기
        val myIntent : Intent = intent
        val receivedMsg: String? = myIntent.getStringExtra("email")
        btn_tomain.text = receivedMsg + "님 종료하시겠습니까?"

        // 1. Data
        // 2. 템플릿(Android 에서 제공해주는 템플릿)
        // 3. Adapter(Data + 템플릿) - Android 에서 제공해주는 Adapter

        var foods = arrayOf("참치회", "아구찜", "파지감자탕", "오돌뼈", "쏘맥")

        var adapter =
            ArrayAdapter<String>(applicationContext, android.R.layout.simple_list_item_1, foods)

        // listivew 에 adapter 연결 ~
        listview.adapter = adapter










        btn_tomain.setOnClickListener {
//            var it : Intent = Intent(this, MainActivity::class.java)
//            startActivity(it)
            finish()

        }


    }


}