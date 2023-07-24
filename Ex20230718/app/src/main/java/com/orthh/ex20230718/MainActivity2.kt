package com.orthh.ex20230718

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import com.orthh.a20230719.R


class MainActivity2 : AppCompatActivity() {

    lateinit var colorList : Array<String>
    lateinit var lv : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        lv = findViewById(R.id.lv_colors)

        colorList = arrayOf("RED", "BLUE", "GREEN")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, colorList)
        lv.adapter = adapter


        // colorlistView.setOnItemClickListener(new OnItemClickListener(){
        //      public void onItemClick(AdapterView view, View view, int i, long l){
        //
        //      }
        // })
        // 매개변수로 onItemClick 찾아가는듯
        // adapterView : 클릭이 일어난 뷰(ListView), view : 내가 누른 항목, i : index, l : id값
        lv.setOnItemClickListener(AdapterView.OnItemClickListener { adapterView, view, i, l ->
            // 메소드 오버라이딩
            // 아이템(항목) 클릭 시 실행되는 코드!
            // 1. 데이터 담을 빈 Intent 생성 (택배 반송이랑 같다고 생각하면됨. 돌아가게 왔으니 안에 안넣어도댐)
            var it_result : Intent = Intent()
            // 2. Intent에 값 담기(index)
            it_result.putExtra("color", i)
            // 3. 다시 돌려주기(RESULT_OK => 약속된 변수)
            setResult(RESULT_OK, it_result)
            // 4. 나는 종료......
            finish()
        })



    }
}