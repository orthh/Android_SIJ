package com.sjy.andserver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
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

            val request = object:StringRequest(
                Request.Method.POST,
                "http://172.30.1.22:8089/login",
                {
                        response ->
                    Log.d("response", response.toString())
                    // 로그인 성공
                    var member = JSONObject(response.toString())
                    Log.d("member", member.toString())
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