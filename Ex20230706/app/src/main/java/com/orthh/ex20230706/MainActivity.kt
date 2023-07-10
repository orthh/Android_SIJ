package com.orthh.ex20230706

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    
    // 필드에서는 변수 생성만, setContentView 에서View생성되고 필드변수에 저장
    // 코틀린에서는 lateinit 해줘야 값 안담고 변수 선언 가능
    lateinit var btn_click : Button
    lateinit var tv_result : TextView
    lateinit var edt_input : EditText
    lateinit var btn_change : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_click = findViewById(R.id.btn_click)
        tv_result= findViewById(R.id.tv_result)
        edt_input = findViewById(R.id.edit_input)
        btn_change = findViewById(R.id.btn_change)

        var isClicked: Boolean = false;
        val defaultTxt: CharSequence = tv_result.text;
        val changeTxt: String = "안녕"
        btn_click.setOnClickListener {
            isClicked = !isClicked
            if(isClicked) tv_result.text = changeTxt else tv_result.text = defaultTxt
        }

//        btn_change.setOnClickListener {
//            tv_result.text = edt_input.text
//            edt_input.text.clear()
//        } // 도전! xml 에서 Event 처리하는 코드로 고치기!
        // 🍠 xml 로 Event 처리하는 방법
        // 1. 버튼을 클릭했을 때 실행될 메소드 정의
        //    - 매개변수를 반드시 View타입으로 생성
        // 2. xml 파일을 열어서 버튼 선택 후 onClick 속성에 메소드 연결



    }

    // public void btnClick(View currentClick){}

    fun btnClick (currentClick :View){
            tv_result.text = edt_input.text
            edt_input.text.clear()
    }







}