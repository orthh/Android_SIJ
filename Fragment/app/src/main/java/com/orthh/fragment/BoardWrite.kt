package com.orthh.fragment

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.orthh.fragment.vo.AndMemberVO
import com.orthh.fragment.vo.BoardVO
import java.io.ByteArrayOutputStream

class BoardWrite : AppCompatActivity() {

    lateinit var etTitle : TextView
    lateinit var etContent : EditText
    lateinit var ibGallery : ImageButton
    lateinit var ibCamera : ImageButton
    lateinit var ivUpload: ImageView
    lateinit var btnWrite : Button

    lateinit var reqQueue : RequestQueue

    lateinit var encodeImgString: String

    val STORAGE_CODE = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board_write)

        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        ibGallery = findViewById(R.id.ibGallery)
        ibCamera = findViewById(R.id.ibCamera)
        ivUpload = findViewById(R.id.ivUpload)
        btnWrite = findViewById(R.id.btnWrite)

        reqQueue = Volley.newRequestQueue(this@BoardWrite)

        val spf = getSharedPreferences("mySPF", Context.MODE_PRIVATE)

        val member = Gson().fromJson(spf.getString("member", ""), AndMemberVO::class.java)



        ibGallery.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "image/*"
            // 다른 액티비티 시작된 후 그 액티비티의 결과를 다시 가져오고 싶을때 사용!
            startActivityForResult(intent, STORAGE_CODE)
        }

        btnWrite.setOnClickListener {
            val inputTitle = etTitle.text.toString()
            val inputContent = etContent.text.toString()
            val writer = member.id

            val request = object:StringRequest(
                Request.Method.POST,
                "http://172.30.1.22:8888/board/write",
                {
                    response ->
                    Log.d("response", response.toString())

                    if(response == "Success"){
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }
                },
                {
                    error ->
                    Log.d("error", error.toString())
                }
            ){
                override fun getParams(): MutableMap<String, String>? {

                    val params: MutableMap<String, String> = HashMap<String, String>()

                    val board = BoardVO(null, inputTitle, inputContent, writer, encodeImgString, null)
                    params.put("board",Gson().toJson(board))

                    return params
                }
            }
            reqQueue.add(request)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode){
            STORAGE_CODE -> {
                // image uri 가져오기
                val selectedImgUri = data?.data

                if(selectedImgUri != null){
                    // uri -> bitmap 으로 변환
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedImgUri)
                    ivUpload.setImageBitmap(bitmap)

                    val options = BitmapFactory.Options()
                    options.inSampleSize = 4

                    val resized =  Bitmap.createScaledBitmap(bitmap, 100, 100, true)
                    encodeBitmapImg(resized)
                }

            }
        }
    }

    // bitmap -> String (Base64)
    private fun encodeBitmapImg(bitmap: Bitmap){

        // ByteArray생성할 수 있는 stream
        val byteArrayOutputStream = ByteArrayOutputStream()
        // bitmap 압축
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)

        val byteOfImg = byteArrayOutputStream.toByteArray()
        // encoding : ByteArray -> String(Base64 - encoding)
        encodeImgString = Base64.encodeToString(byteOfImg, Base64.DEFAULT)

    }
}