package com.orthh.ex20230711b

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Chronometer
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    // 1. Button 25개 findViewById => 배열, for문
    //  - 변수로 생성하면 노가다, 순서가 없어서 for문도 못돌림...
    // 2. 1~25까지 저장된 배열 생성! 랜덤으로 섞기!
    // 3. Button에 숫자 띄우기!
    // 4. ClickEvent 처리하기
    //  - 사라지기!
    //  - 순서에 맞는지 확인하기!
    //  - 25버튼 눌렀으면 멈춤!
    // var temp = arrayOf(1,2,3)
    var btns = arrayOfNulls<Button>(25)
    var nums = Array(25){i -> i+1} // 람다식으로 1~25까지 채운 배열 생성
    var cnt: Int = 1
    lateinit var time : Chronometer


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(R.layout.gamelayout)

        time = findViewById(R.id.tv_time)
        time.start()

        // 숫자 섞기
        nums.shuffle()
        //
        for(x in 0 .. 24){}
        for(index in 0 until 25){
            // id명(String)으로 주소값(int)알아내기
            var id : Int = resources.getIdentifier("btn" + (index + 1), "id", packageName)
            btns[index] = findViewById(id)
            btns[index]!!.text = nums[index].toString()
            // !! => null값이 아닐떄만 메소드 호출
            btns[index]!!.setOnClickListener {
                if(cnt.toString() == btns[index]!!.text){
                    btns[index]!!.visibility = View.INVISIBLE // view를 화면에서 지우는 코드
                    cnt++
                    if(cnt == 25) time.stop()
                }
            }
        }
        // 배열이랑 함께쓰는 for 문
        for((idx,btn) in btns.withIndex()){}

    }

}