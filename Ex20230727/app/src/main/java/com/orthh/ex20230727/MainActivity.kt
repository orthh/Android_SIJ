package com.orthh.ex20230727

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var tv_start1 : TextView
    lateinit var tv_start2 : TextView
    lateinit var tv_start3 : TextView
    lateinit var btn_1 : Button
    lateinit var btn_2 : Button
    lateinit var btn_3 : Button
    lateinit var edt_num : EditText
    lateinit var btn_4 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Thread 는 class로 설계한다!
        // 때문에!!!! 객체를 생성해야 한다!!
        tv_start1 = findViewById(R.id.textView)
        tv_start2 = findViewById(R.id.textView2)
        tv_start3 = findViewById(R.id.textView3)
        edt_num = findViewById(R.id.edt_num)
        btn_1 = findViewById(R.id.button)
        btn_2 = findViewById(R.id.button2)
        btn_3 = findViewById(R.id.button3)
        btn_4 = findViewById(R.id.btn_gogo)



        btn_1.setOnClickListener {
            var th1 = cntThread(tv_start1, 30)
            th1.start()
        }

        btn_2.setOnClickListener {
            var th2 = cntThread(tv_start2, 20)
            th2.start()
        }
        btn_3.setOnClickListener {
            var th3 = cntThread(tv_start3, 10)
            th3.start()
        }
        btn_4.setOnClickListener {
            var th4 = cntThread(edt_num, Integer.parseInt(edt_num.text.toString()))
            th4.start()
        }


    }

    var cntHandler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // 메시지를 받아서 처리하는 곳!
            var tv: TextView = msg.obj as TextView
            tv.text = msg.arg1.toString()

        }
    }

    inner class cntThread(var tv: TextView, var cnt: Int) : Thread(){

        // 1. Thread 클래스 상속! (extends)
        // 2. Runnable 인터페이스 구현! (implements)
        override fun run() {

            // super.메소드이름
            // => 삭제해도 되는 경우(매개변수 없으면), 안되는경우(매개변수가 있으면)
            for(i in cnt downTo 1){
                // 1. 메시지 생성!
                var msg : Message = Message()
                // 2. 데이터 셋팅!
                msg.arg1 = i
                msg.obj = tv
                // 3. 핸들러한테 전송~
                cntHandler.sendMessage(msg)
                Thread.sleep(500)
            }
        }
    }



}