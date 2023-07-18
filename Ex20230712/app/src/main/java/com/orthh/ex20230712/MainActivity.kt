package com.orthh.ex20230712

import android.app.SearchManager
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class MainActivity : AppCompatActivity() {
    // iv_kakaopage 이미지뷰 찾기!
    // tv_googlekakao 텍스트뷰 찾기!
    lateinit var iv_kakaopage : ImageView
    lateinit var tv_googlekakao : TextView
    lateinit var tv_sms : TextView
    lateinit var tv_call : TextView
    lateinit var btn_next : Button
    lateinit var edt_email: EditText
    lateinit var edt_pw: EditText
    lateinit var members : HashMap<String, String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // members = new HashMap()
        members = HashMap()
        members.put("dldmsql", "1214")
        members.put("admin", "12345")
        members.put("smhrd", "12345")
        members.put("test", "123")
        members.put("diolee", "111")

        iv_kakaopage = findViewById(R.id.iv_kakaopage)
        tv_googlekakao = findViewById(R.id.tv_googlekakao)
        tv_sms = findViewById(R.id.tv_sms)
        tv_call = findViewById(R.id.tv_call)
        btn_next = findViewById(R.id.btn_next)
        edt_email = findViewById(R.id.edt_email)
        edt_pw = findViewById(R.id.edt_pw)

        iv_kakaopage.setOnClickListener{
            // Intent!!!
            // 카카오 웹페이지 이동~
            // 1. intent 생성
            var it_kakao : Intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.kakaocorp.com/page/"))
            // Intent it_kakao = new Intent(해야할일(Action), 데이터(Uri))
            // Intent 생성할 때 매개변수 2개 들어감!
            
            // 2. intent 실행
            startActivity(it_kakao)
            // 중요! startActivities?? 애 아님......
        }
        // 검색
        tv_googlekakao.setOnClickListener{
            // 1. Intent 생성
            var it: Intent = Intent(Intent.ACTION_WEB_SEARCH)
            // 2. putExtra 함수 사용, 데이터 담기
            it.putExtra(SearchManager.QUERY, "아구찜")
            // 3. Intent 실행!
            startActivity(it)
        }

        // 문자보내기
        tv_sms.setOnClickListener {
            // 문자보내는 페이지까지 이동!
            // 실제로 문자 보내야된다... => 문자보내는 업체 사용해야함
            // 1. Intent 생성
            // smsto: 010~~~ => MIME type
            var it : Intent = Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:010-4393-9206"))
            // 2. putExtra 함수 사용 문자 내용 작성
            it.putExtra("sms_body", "안녕!")
            // 3. Intent 실행
            startActivity(it)
        }

        // 전화걸기
        tv_call.setOnClickListener {

            // 1. Intent 생성 (Action, Data)


            // 2. Intent 실행

            // 튕겨야 맞음!
            // 왜냐하면 Android에는 예민한 기능
            // 권한요청을 해서 사용자가 허용을 해야 수행할 수 있도록 Android가 설계해놨음!

            // 1. 이미 권한을 승인하지 않았는지 검사!
            // 2. 승인하지 않았다면 다이얼로그 띄움!
            // 3. Manifest.xml 파일 여러서 permission 태그 추가!

            if(ActivityCompat.checkSelfPermission(applicationContext
                    , android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this
                    ,arrayOf(android.Manifest.permission.CALL_PHONE), 0)
            }
            var it: Intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:010-4393-9206"))
            startActivity(it)
        }

        // 로그인버튼 2번 페이지로 이동
        btn_next.setOnClickListener {
            var it_next: Intent = Intent(this, SubActivity::class.java)
            // 1. id가 존재하는지
            if(members.containsKey(edt_email.text.toString())){
                // 해당하는 key값이 있으면 true, 없으면 false
                if(members.get(edt_email.text.toString()).equals(edt_pw.text.toString())){
                    it_next.putExtra("email", edt_email.text.toString())
                    startActivity(it_next)
                }

            }else{
                Toast.makeText(this, "존재하지 않는 아이디입니다", Toast.LENGTH_SHORT).show()
            }
        }


    }
}