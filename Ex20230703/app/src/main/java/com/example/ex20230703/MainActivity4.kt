package com.example.ex20230703

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
// AppCompatActivity 상속받음
class MainActivity4 : AppCompatActivity() {
    // 상속받은 AppCompatActivity 의 onCreate메소드 override함
    override fun onCreate(savedInstanceState: Bundle?) {
        // 화면 구성 준비
        super.onCreate(savedInstanceState)
        // View를 세팅하기 이전에 findViewById를 하는건 불가능
        // NPE 발생
        setContentView(R.layout.activity_main4)  // xml 파일과 kt파일을 연결

        // btnPlus를 클릭했을 때, 이벤트가 일어나는지 확인
        // xml에 부여했던 id값을 Class에서 바로 참조하는 건 불가능!
        // xml에 부여한 id --> R.id에 저장이 된다(16진수의 랜덤한 값)
        // --> Class에서 findViewById()
        // Id값(16진수의 랜덤한 값)을 통해서 View를 찾아온다!
//        val btnPlus : Button = findViewById<Button>(R.id.btnPlus)
        val btnPlus : Button = findViewById(R.id.btnPlus)
        // 버튼 타입의 변수 btnPlus를 생성하고 R.id.btnPlus로 찾은 View를 저장해라
        
        // TypeMismatchException : 내가 찾아여로는 View랑
        // 변수의 View타입이 일치하지 않을경우에 발생하는 예외상황

        // 1) Toast창을 Emulator에 띄워보자(문구 : 클릭!!!)
        // 2) Log를 통해 확인해보자
        btnPlus.setOnClickListener {
            // btnPlus를 클릭했을 때 실행시킬 코드
            Toast.makeText(this@MainActivity4,"클릭!!",Toast.LENGTH_SHORT).show()
            // 1) context : 화면정보(어디에 토스트를 보여지게 만들거니?)
            // this@MainActivity4
            // 2) text : CharSequence! ---> String의 문구
            // Int자료형을 띄울 수는 있음 단, View의 Id값만 가능!
            // 3) duration : 토스트창의 지속시간(몇초동안 띄울꺼니?)
            // + show() : Toast 창이 화면에 보임
        }

    }
}