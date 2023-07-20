package com.orthh.ex20230719

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import com.google.gson.Gson
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var tv_nick : TextView
    lateinit var lv_result : ListView
    lateinit var btn_write: Button
    lateinit var btn_login: Button
    lateinit var boardList : Array<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv_nick = findViewById(R.id.textView)
        lv_result = findViewById(R.id.lv_result)
        btn_write = findViewById(R.id.button)
        btn_login = findViewById(R.id.button2)

        boardList = arrayOf("1. Android 짱 재밌음", "아님 어려움...", "그래도 재밌음!!", "4. 이디오 겁나 귀여움")

        // listView 만들때!!
        // 1. 데이터(배열/ArrayList)
        // 2. 템플릿(.xml)
        // 3. 어댑터(.kt : BaseAdapter 상속)


        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, boardList)
        lv_result.adapter = adapter

        btn_login.setOnClickListener {
            val it_login = Intent(this, MainActivity2::class.java)
            frLauncher.launch(it_login)


        }




    }
    var frLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == RESULT_OK){
            var result: String? = it.data!!.getStringExtra("member")
            Log.d("result2", result.toString())
            var obj = JSONObject(result)
            var id = obj.getString("id")
            var pw = obj.getString("pw")
            // 로그인 성공시
            tv_nick.text = id + "님 환영합니다!!"
            btn_write.visibility = View.VISIBLE
            btn_login.text = "로그아웃"
        }
        Log.d("result", it.toString())
    }
}