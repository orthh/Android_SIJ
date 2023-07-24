package com.orthh.ex20230719

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson

class MainActivity2 : AppCompatActivity() {

    lateinit var edt_id: EditText
    lateinit var edt_pw: EditText
    lateinit var btn_submit: Button
    lateinit var users : HashMap<String,String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        edt_id = findViewById(R.id.editTextText)
        edt_pw = findViewById(R.id.editTextTextPassword)
        btn_submit = findViewById(R.id.button3)

        users = HashMap<String,String>()

        users.put("smhrd", "12345")
        users.put("admin", "12345")
        users.put("test", "12345")
        users.put("cocomo", "12345")
        users.put("knight", "12345")

        btn_submit.setOnClickListener {
            var id = edt_id.text.toString()
            var pw = edt_pw.text.toString()
            if(users.get(id).isNullOrEmpty()){
                // 아이디 없음
                Toast.makeText(this, "id가 존재하지 않습니다", Toast.LENGTH_SHORT).show()
            }else{
                // 아이디 있음
                if(users.get(id).equals(pw)){
                    // 로그인 성공
                    var member = MemberVO(id, pw)
                    var it_result = Intent()
                    it_result.putExtra("member", Gson().toJson(member))
//                    it_result.putExtra("id", member.id)
                    setResult(RESULT_OK, it_result)
                    finish()
                }else{
                    // 비밀번호오류
                    Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }


    }
}