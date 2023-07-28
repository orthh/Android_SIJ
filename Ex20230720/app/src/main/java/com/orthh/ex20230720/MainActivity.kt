package com.orthh.ex20230720

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    // listView 의 업그레이드 버전
    // RecyclerView를 만들어 보자!
    // Custom RecyclerView
    // 원리
    // Data의 개수만큼 Template를 복사하여 RecyclerView안에 배치!
    // 리액트의 map 이랑 비슷한듯
    // => who? Adapter가 함!

    // 1. Data (VO, ArrayList)
    // 2. Template(.xml)
    // 3. Adapter
    // - ViewHolder도 만들어야함

    // 이미지 -> int(주소), 이름 -> String, 메시지 -> String, 시간 : String --> VO
    // VO 담은 ArrayList

    lateinit var rv: RecyclerView
    lateinit var btn_send : Button
    lateinit var edt_msg: EditText

    // FireBase => 구글 클라우드 서버
    // 클라우드 서버?
    // 구글에서 일정량의 저장소와 서버를 구축해두고 Android 개발자에게 제공하는 서비스!
    // 목적 : Android 개발자가 서버를 구현하는 번거로움을 해소!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv_result)
        btn_send = findViewById(R.id.btn_send)
        edt_msg = findViewById(R.id.edt_msg)

        // firebase db 연동
        // App에 연결되어있는 FireBase DataBase 객체 가져오기
        val database = Firebase.database 
        // Database 경로 가져오기
        val myRef = database.getReference("message")
        // 해당 경로에 데이터 저장하기

        
        
        
        
        var data = ArrayList<KakaoVO>()

        // add 함수 사용하여 메시지 5개 저장~
//        myRef.push().setValue(KakaoVO(R.drawable.img1, "김혁", "안녕하세요^^", "오후 4:17"))
//        myRef.push().setValue(KakaoVO(R.drawable.img2, "신지훈", "다들 안녕하세요 반갑습니다.", "오후 4:19"))
//        myRef.push().setValue(KakaoVO(R.drawable.img3, "신지영", "비가 많이오네요", "오후 4:21"))
//        myRef.push().setValue(KakaoVO(R.drawable.img4, "이지희", "네 안녕하세요다들", "오후 4:23"))
//        myRef.push().setValue(KakaoVO(R.drawable.img5, "서현록", "하이요", "오후 4:33"))


        // 내가 보낸 채팅 오른쪽에 띄우는법 !
        // 1. template.xml 파일에 오른쪽 톡 추가! (중요! 같은 파일에 추가 할 것!)
        // 2. 현재 로그인한 ID를 adapter 생성자로 전송 => 메시지 주인
        // 3. adapter 클래스의 onBindView메소드에서 data.get(position).name(메시지주인)과 생성자로
        // 전달된 id를 비교
        // 4. 일치한다면 왼쪽 뷰들은 전부 gone, 오른쪽 뷰들은 visible
        //   template에 뷰가 추가됐으니 ViewHolder도 수정 필요!!!
        var adapter: KakaoAdapter = KakaoAdapter(applicationContext, R.layout.template, data)

        // layoutManager 세팅
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapter

        btn_send.setOnClickListener {
            myRef.push().setValue(KakaoVO(R.drawable.img1, "펑이", edt_msg.text.toString(), "오후 16:38"))
            // adapter 새로고침
            adapter.notifyDataSetChanged()
            // 스크롤 옮기기
            rv.smoothScrollToPosition(data.size - 1)
            // EditText 비우기
            edt_msg.text.clear()

        }

        myRef.addChildEventListener(ChildEvent(data, adapter))

    }
}