package com.orthh.ex20230707

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // 필드
    lateinit var btn_plus : Button
    lateinit var btn_minus : Button
    lateinit var tv_count : TextView
    private var cnt : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tv_count = findViewById(R.id.textView)
    }

    fun btnClick(currentClick :View){
        // 버튼이 늘어날 때마다 메소드를 하나씩 계속 추가해야하나..?
        // click메소드 하나로 여러개의 Event를 처리할 수 있음!
        // 매개변수 currentClick => 사용자가 클릭한 View

        if(currentClick.id === R.id.button2){
            cnt++
        }else if(currentClick.id === R.id.button){
            if(cnt > 0) cnt--
        }
        tv_count.text = cnt.toString()
    }



}