package com.orthh.ex20230705

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Button 찾기
        val btn1 : Button = findViewById(R.id.button)
        val btn2 : Button = findViewById(R.id.button2)
        // 2. TextView 찾기
        val tv1 : TextView = findViewById(R.id.textView2)
        // 3. Button 눌렀을 떄를 감지(Event)할 Listener 달아주기
        // 4. Listener 안에서 TextView의 text 속성 바꾸기!
        var isClicked : Boolean = false;
        val defaultText = tv1.text;
        btn1.setOnClickListener {
            tv1.text="안녕 안드" // public 변수 접근
            tv1.setTextColor(Color.RED) // 메소드로 접근
//            tv1.setTextColor(Color.parseColor("#6B9900"));
//            tv1.setTextColor(Color.rgb(1,1,1));

        }
        btn2.setOnClickListener {
            tv1.text= defaultText
            tv1.setTextColor(Color.BLACK)
        }
    }
}