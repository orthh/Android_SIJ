package com.orthh.ex20230707b

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

class MainActivity : AppCompatActivity() {

    lateinit var iv1 : ImageView
    // 0부터 사진 갯수
//    private var pictures: Array<Int> = Array(3){i->i}
    var index = 0
    private var pics : Array<Int> = arrayOf(R.drawable.img0, R.drawable.img1, R.drawable.img2)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iv1 = findViewById(R.id.imageView)
    }
    // 1. btnClick 메소드 만들기
    // 2. ImageView 찾기 (이전에는 TextView의 text속성을 바꿨음. 이번에는 ImageView의 ImageResource를 바꿔줌)
    // 3. 클릭이 일어난 버튼의 id값에 따라 ImageView의 ImageResource를 바꿔주면 됨
    // - 이미지뷰.setImageResource(R.drawable.파일명)
    // 1, 2, 3 버튼 이벤트 구현하기!


    // imageResId 가져오는 함수
    private fun handleImageResId(idx: Int): Int {
        val imageName = "img$idx"
        return resources.getIdentifier(imageName, "drawable", packageName)
    }

    // 숫자 버튼 클릭시 변경
    fun btnClick(currentView: View){
        val button = currentView as Button
        val currentValue = button.text.toString().toInt()
        iv1.setImageResource(handleImageResId(currentValue - 1))
        index = currentValue - 1;
    }

    // 좌우 슬라이드 버튼 이미지 변경하는 함수
    fun movePicture(currentView :View){
        if(currentView.id === R.id.btn_pre){
            // left버튼
            if(index === 0)index = pics.size -1
            else index = --index % (pics.size)
            index = abs(index)
            iv1.setImageResource(pics[index])
        }else{
            // right버튼
            index = ++index % (pics.size)
            iv1.setImageResource(pics[index])

        }
    }

    fun ivClick(currentView: View){

    }
}