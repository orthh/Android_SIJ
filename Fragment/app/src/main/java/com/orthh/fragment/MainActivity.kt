package com.orthh.fragment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.orthh.fragment.vo.AndMemberVO

class MainActivity : AppCompatActivity() {

    lateinit var bnv: BottomNavigationView
    lateinit var fl: FrameLayout

    lateinit var tvId: TextView
    lateinit var btnLogout:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bnv = findViewById(R.id.bnv)
        fl = findViewById(R.id.fl)
        tvId = findViewById(R.id.tvId)
        btnLogout = findViewById(R.id.btnLogout)

        val spf = getSharedPreferences("mySPF", Context.MODE_PRIVATE)
        // editor 생성
        val editor = spf.edit()
        // editor 를 통해서 현재 로그인한 회원의 정보 저장
        val memberValue = spf.getString("member", "default_value")
        val member = Gson().fromJson(memberValue, AndMemberVO::class.java)
        tvId.text = member.id + "님 환영합니다❤"
        // 로그아웃
        btnLogout.setOnClickListener {
            // SharedPreference 생성

//            editor.clear()
            editor.remove("member");
            editor.commit() // 완료

            // MainActiviry로 전환(Intent)
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        // supportFragmentManager 활용 transaction 생성
        // transaction 을 통해 프래그먼트 교체 -> commit(완료)
        // MainActivity로 전환되자마자 프레그먼트 1번으로 바꾸기!
        supportFragmentManager.beginTransaction().replace(
            R.id.fl,
            Fragment1()
        ).commit()

        // bnv에서 선택한 메뉴에 따라 fl에 표시할 Fragment를 갈아 끼우기!
        bnv.setOnItemSelectedListener {
            Log.d("id", it.itemId.toString())

            when (it.itemId) {
                R.id.tab1 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment1()
                    ).commit()
                }
                R.id.tab2 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment2()
                    ).commit()
                }

                R.id.tab3 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment3()
                    ).commit()
                }

                R.id.tab4 -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fl,
                        Fragment4()
                    ).commit()
                }
            }

            // boolean : true(이벤트 인식이 더 좋음!) / false(이벤트 인식)
            true
        }



    }
}