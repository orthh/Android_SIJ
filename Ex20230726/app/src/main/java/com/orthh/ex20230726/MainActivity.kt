package com.orthh.ex20230726

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var rv: RecyclerView
    lateinit var btn_send : Button
    var data = ArrayList<LinkVO>()
    lateinit var adapter: LinkAdapter // Adapter 변수 선언

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv = findViewById(R.id.rv_result)
        btn_send = findViewById(R.id.btn_send)



        val database = Firebase.database
        val myRef = database.getReference("message")

        data.add(LinkVO("네이버", "https://www.naver.com"))
        data.add(LinkVO("구글", "https://www.google.com"))
        data.add(LinkVO("스마트인재개발원", "https://www.smhrd.com"))


        adapter = LinkAdapter(applicationContext, R.layout.template, data) // Adapter 초기화
        rv.layoutManager = LinearLayoutManager(applicationContext)
        rv.adapter = adapter

        btn_send.setOnClickListener {
            val intent = Intent(this@MainActivity, AddLink::class.java)
            frLauncher.launch(intent)
        }


    }
    var frLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        Log.d("ss", it.toString())
        if(it.resultCode == RESULT_OK){
            data.add(LinkVO(it.data!!.getStringExtra("linkName"), it.data!!.getStringExtra("urlName")))
            adapter.notifyDataSetChanged()
        }
    }
}