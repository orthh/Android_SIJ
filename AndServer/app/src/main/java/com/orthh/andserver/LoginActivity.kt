package com.orthh.andserver

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.orthh.andserver.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    lateinit var etId : EditText
    lateinit var etPw : EditText
    lateinit var btnLogin:Button

    lateinit var reqQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etId = findViewById(R.id.etId)
        etPw = findViewById(R.id.etPw)
        btnLogin = findViewById(R.id.btnLogin)

        reqQueue = Volley.newRequestQueue(this@LoginActivity)

        btnLogin.setOnClickListener {
            val inputId = etId.text.toString()
            val inputPw = etPw.text.toString()

            val request = object: StringRequest(
                Method.POST, // post 요청시
                "http://172.30.1.22:8888/login", // http 요청시 manifest.xml 에
                // android:usesCleartextTraffic="true"
                {
                        response ->
                    Log.d("response", response.toString())
                    if(response == "Success"){
                        // 성공
                    }else{ // response == "Fail"
                        // 실패
                        Toast.makeText(this, "로그인 실패", Toast.LENGTH_SHORT).show()
                    }
                },{
                        error -> Log.d("error", error.toString())
                    Toast.makeText(this, "오류 발생!!!", Toast.LENGTH_SHORT).show()
                }
            ){
                override fun getParams() : MutableMap<String, String> {
                    val params : MutableMap<String, String> = HashMap<String, String>()
                    val amv : AndMemberVO = AndMemberVO(inputId, inputPw, null, null)
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