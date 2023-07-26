package com.orthh.ex20230726

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class AddLink : AppCompatActivity() {

    lateinit var edt_linkName :EditText
    lateinit var edt_urlName :EditText
    lateinit var btn_add : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_link)

        edt_linkName = findViewById(R.id.edt_linkName)
        edt_urlName = findViewById(R.id.edt_urlName)
        btn_add = findViewById(R.id.btn_add)


        btn_add.setOnClickListener {
            var it: Intent = Intent(this@AddLink, MainActivity::class.java)
            it.putExtra("linkName", edt_linkName.text.toString())
            it.putExtra("urlName", edt_urlName.text.toString())
            setResult(RESULT_OK, it)
            finish()
        }



    }
}