package com.orthh.ex20230718

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {

    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn = findViewById(R.id.button)

        btn.setOnClickListener {
            // startActivity => 편도
            // ForResultLauncher => 왕복
            val it_color = Intent(this, MainActivity2::class.java)
            frLauncher.launch(it_color)
        }
        
    }

    var frLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        // Launcher 구역
        // 갔다 돌아왔을때 담아준 데이터가 it 에 들어있음
        
        // 1. 이상없이 돌아왔는지
        if(it.resultCode == RESULT_OK){
            var result: Int = it.data!!.getIntExtra("color", -1)
        }
        // 2. 담아준 데이터가 있는지
        Log.d("eee", it.toString())

    }    
}