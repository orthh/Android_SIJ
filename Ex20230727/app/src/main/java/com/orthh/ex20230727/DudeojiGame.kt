package com.orthh.ex20230727

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.widget.ImageView
import android.widget.TextView
import java.util.Random

class DudeojiGame : AppCompatActivity() {
    var imgs = arrayOfNulls<ImageView>(9) // ImageView 타입의 배열 9칸 생성하고 null 로 초기화
    var threads = arrayOfNulls<dudeojiThread>(9) // Thread 객체 9개 저장
    lateinit var tv_score: TextView
    lateinit var tv_time: TextView
    var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dudeoji_game)

        tv_score = findViewById(R.id.tv_score)
        tv_time = findViewById(R.id.tv_time)
        cntThread(tv_time,30).start()

        // 생성될 때 ImageView를 전달받는 Thread 를 설계해주세요!
        // 알고리즘 =>

        for(index in 0 until 9){
            var id = resources.getIdentifier("imageView" + (index + 1), "id", packageName)
            imgs[index] = findViewById(id)
            imgs[index]!!.tag = "off"

            // 배열에 생성된 Thread 누적~
            threads[index] = dudeojiThread(imgs[index]!!)
            threads[index]!!.start()


            // Thread 종료시키는 방법
            // 1. interrupt 발생시킨다.
            // 2. Thread 내부에서는 interrupt exception이 발생!
            // 두더지 클릭했을 때 on? off? 판단 R.drawable.___ 주소로이미지 변경했음
            // Android 에서 이미지끼리 비교하는 방법은 Drawable 이라는 객체로 변경 후 이미지 처리
            // 일이 커짐 => View에 tag라는 변수가 있음 ! 애를 활용
            imgs[index]!!.setOnClickListener {
                if(imgs[index]!!.tag.toString() == ("on")){ // 두더지에 "on" 태그가 걸려있다면
                    score++
                }else{
                    score--
                }
                tv_score.text = score.toString()
                imgs[index]!!.tag = "off"
            }


        }


    }
    var dodoHandelr = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            // 오버라이딩을 위한 구간
            super.handleMessage(msg)
//            msg.obj = dodo
            // obj변수의 자료형은 object!
            // dodo는 이미지뷰 타입의 객체인데, 얘가 object타입으로 형변환 됨-> 업캐스팅
            // 왜 msg 설계할 때 object로 해놨을까? -> 모든 클래스들의 최상위 클래스이기 때문에 다 받아주려고

            (msg.obj as ImageView).setImageResource(msg.arg1)
            // 업캐스팅 되어 있는 상태에서 하위클래스로 형변환
            // drawable : 안드로이드에서 사용하는 이미지 자료형 (비트맵처럼)

            (msg.obj as ImageView).tag = if(msg.arg1 == R.drawable.on) "on" else "off"

        }
    }

    inner class dudeojiThread(var dodo: ImageView) : Thread(){
        override fun run() {

            try {
                while(true){
                    // 두더지마다 랜덤한 offTime 부여
                    var offTime = Random().nextInt(5000) + 500
                    Thread.sleep(offTime.toLong())

                    // 올라가는 이미지로 변경!
                    var msg = Message()
                    msg.obj = dodo;
                    msg.arg1 = R.drawable.on
                    dodoHandelr.sendMessage(msg)

                    var onTime = Random().nextInt(1000) + 500 // 0.5~ 1.5사이
                    Thread.sleep(onTime.toLong())

                    msg = Message()
                    msg.obj = dodo
                    msg.arg1 = R.drawable.off
                    dodoHandelr.sendMessage(msg)


                }
            }catch (e : InterruptedException){
                return // 메소드 종료
                // 메소드는 return 키워드를 만나는 그 즉시 호출한 곳으로 돌아간다.
            }

        }
    }
    var cntHandler = object : Handler(Looper.getMainLooper()){
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            // 메시지를 받아서 처리하는 곳!
            var tv: TextView = msg.obj as TextView
            tv.text = msg.arg1.toString()
            // Thread 중지
            if(msg.arg1 == 1){
                for(temp in threads){
                    // in 오른쪽에 적힌 배열에서 순서대로 하나씩 꺼내 temp에 저장
                    temp!!.interrupt()
                }
            }
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