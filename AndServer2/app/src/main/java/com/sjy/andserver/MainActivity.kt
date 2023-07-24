package com.sjy.andserver

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    lateinit var btnSubmit : Button
    lateinit var etId : EditText
    lateinit var etPw : EditText
    lateinit var etTel : EditText
    lateinit var etBirth : EditText

    lateinit var reqQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnSubmit = findViewById(R.id.btnLogin)
        etId = findViewById(R.id.etLoginId)
        etPw = findViewById(R.id.etLoginPw)
        etTel = findViewById(R.id.etTel)
        etBirth = findViewById(R.id.etBirth)

        reqQueue = Volley.newRequestQueue(this)

        btnSubmit.setOnClickListener {
            //버튼을 클릭하면 사용자가 작성한 값 가져오기 -> NODE -> 응답(가입 성공/실패)
            val inputId = etId.text.toString()
            val inputPw = etPw.text.toString()
            val inputTel = etTel.text.toString()
            val inputBirth = etBirth.text.toString()

            //object: -> 무명객체를 생성하는 키워드
            //  StringRequest를 구현하는 객체를 생성하겠다!
            //  일반적으로는 인터페이스에서는 객체 생성 불가 -> 구현을 위한 클래스를 만들어서 객체를 생성함
            //  그런데 B타입의 객체를 하나만 쓰고 싶을 때 클래스를 만들기에는 비효율적임
            //  이때 object 사용 > 한번만 쓸 객체를 구현할 때 사용!
            val request = object:StringRequest(

                Request.Method.POST,
                //manifest.xml -> android:usesCleartextTraffic="true" 추가해야함!
                "http://172.30.1.22:8089/join", //http 요청
                {
                    response ->
                    Log.d("response", response.toString())
                    if(response == "Success"){
                        //Login Activity로 전환
                        var it_login : Intent = Intent(this, LoginActivity::class.java)
                        startActivity(it_login)
                    }else{
                        Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                    }
                },
                {
                    error ->
                    Log.d("error", error.toString())
                }

            ){
                //post : 데이터를 같이 보내기 위해 작성하는 부분
                //                              반환타입
                override fun getParams() : MutableMap<String, String>{
                    //Map : 정적, MutableMap : 유연하게 사용가능한 Map
                    val params:MutableMap<String, String> = HashMap<String, String>()

                    //MutableMap은 interface로 객체 생성 불가
                    val amv = AndMemberVO(inputId, inputPw, inputTel, inputBirth)

                    //VO 자체는 타 플랫폼에서 사용하기에는 어려움 -> JSON형태로 변환후 보내야 함
                    //AndMemberVO(Object)-> JSON 형태로 변환
                    //GSON(goole에서 만든 json을 쉽게 다룰 수 있도록 만든 라이브러리) 사용
                    params.put("AndMember", Gson().toJson(amv)) //회원을 하나로 묶어서 보냄 (memberVO)
                    return params
                }
            }
            reqQueue.add(request)
        }
    }
}