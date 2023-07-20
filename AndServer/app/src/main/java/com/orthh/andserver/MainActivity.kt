package com.orthh.andserver

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
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var etId :EditText
    lateinit var etPw : EditText
    lateinit var etTel : EditText
    lateinit var etBirth : EditText
    lateinit var btnJoin : Button

    lateinit var reqQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etId = findViewById(R.id.etId)
        etPw = findViewById(R.id.etPw)
        etTel  = findViewById(R.id.etTel)
        etBirth = findViewById(R.id.etDate)
        btnJoin = findViewById(R.id.btnJoin)

        reqQueue = Volley.newRequestQueue(this@MainActivity)

        // 버튼을 클릭하면 사용자가 작성한 값 가져오기 -> NODE -> 응답(가입이 성공/실패)
        btnJoin.setOnClickListener {
            val inputId = etId.text.toString()
            val inputPw = etPw.text.toString()
            val inputTel = etTel.text.toString()
            val inputBirth = etBirth.text.toString()
    
            // object: => 무명객체 생성
            // 예를들어 인터페이스를 구현해야할때 임시적으로 한번만 구현
            // 인터페이스 A가 있다면 new A 안됨. class B implements A 생성하고 new A() 해야하는데
            // 이걸 한번에 쉽게 하는것
            // StringRequest 구현하는 객체 생성
            val request = object:StringRequest(
                Request.Method.POST, // post 요청시
                "http://172.30.1.22:8888/join", // http 요청시 manifest.xml 에
                                                     // android:usesCleartextTraffic="true"
                {
                    response ->
                    Log.d("response", response.toString())
                },{
                    error -> Log.d("error", error.toString())
                    Toast.makeText(this, "오류 발생!!!", Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams() : MutableMap<String, String> {
                    val params : MutableMap<String, String> = HashMap<String, String>()
                    val amv : AndMemberVO = AndMemberVO(inputId, inputPw, inputTel, inputBirth)
                    // JSON 형태로 바꿔서보낸다.(플랫폼에 상관없이 쓰기위해)
                    // AndMemberVO(Object) -> JSON 형태로 변환 => GSON 라이브러리 사용
                    params.put("AndMember", Gson().toJson(amv))
                    return params
                }
            }

            reqQueue.add(request)
        }

    }
}