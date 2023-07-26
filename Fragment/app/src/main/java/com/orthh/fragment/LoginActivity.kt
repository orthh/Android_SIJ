package com.orthh.fragment

import android.content.Context
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
import com.orthh.fragment.vo.AndMemberVO
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    lateinit var etId : EditText
    lateinit var etPw : EditText
    lateinit var btnLogin : Button

    lateinit var reqQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etId = findViewById(R.id.etLoginId)
        etPw = findViewById(R.id.etLoginPw)
        btnLogin = findViewById(R.id.btnLogin)

        reqQueue = Volley.newRequestQueue(this)

        btnLogin.setOnClickListener {

            val inputId = etId.text.toString()
            val inputPw = etPw.text.toString()

            val request = object: StringRequest(
                Request.Method.POST,
                "http://172.30.1.22:8888/member/login",
                {
                        response ->
                    Log.d("response", response.toString())
                    if(response == "Fail"){
                        Toast.makeText(this@LoginActivity,"fail", Toast.LENGTH_SHORT ).show()
                    }else{
                        // 로그인 성공
                        var member = JSONObject(response.toString())
                        Log.d("member", member.toString())

                        // SharedPreference 생성
                        val spf = getSharedPreferences("mySPF", Context.MODE_PRIVATE)
                        // editor 생성
                        val editor = spf.edit()
                        // editor 를 통해서 현재 로그인한 회원의 정보 저장
                        editor.putString("member", member.toString())
                        editor.commit() // 완료

                        // MainActiviry로 전환(Intent)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }


                },
                {
                        error ->
                    Log.d("error", error.toString())
                }
            ){
                override fun getParams() : MutableMap<String, String>{
                    val params:MutableMap<String, String> = HashMap<String, String>()
                    val loginAmv = AndMemberVO(inputId, inputPw, null, null)
                    params.put("AndMember", Gson().toJson(loginAmv))
                    return params
                }
            }

            request.setShouldCache(false)
            reqQueue.add(request)
        }
    }
}