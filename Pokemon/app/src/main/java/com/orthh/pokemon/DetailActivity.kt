package com.orthh.pokemon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import org.json.JSONObject

class DetailActivity : AppCompatActivity() {

    lateinit var iv_img : ImageView
    lateinit var tv_name : TextView
    lateinit var tv_type1 : TextView
    lateinit var tv_type2 : TextView
    lateinit var tv_height : TextView
    lateinit var tv_weight : TextView
    lateinit var tv_type2_title : TextView

    lateinit var reqQueue : RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        
        // component 가져오기 -> 이미지, 텍스트
        iv_img = findViewById(R.id.iv_img)
        tv_name = findViewById(R.id.tv_name)
        tv_type1 = findViewById(R.id.tv_type1)
        tv_type2 = findViewById(R.id.tv_type2)
        tv_height = findViewById(R.id.tv_height)
        tv_weight = findViewById(R.id.tv_weight)
        tv_type2_title = findViewById(R.id.tv_type2_title)

        reqQueue = Volley.newRequestQueue(this@DetailActivity)


        // intent -> id(상세정보 가져올 때 사용), 이미지 경로(Glide), 이름
        var it = getIntent()
        var pokemonId = it.getIntExtra("pokemonId",-1)
        var pokemonImgPath = it.getStringExtra("pokemonImgPath")
        var pokemonNm = it.getStringExtra("pokemonNm")
        var detailUrl = "https://pokeapi.co/api/v2/pokemon/$pokemonId"
        val request = object : StringRequest(
            Request.Method.GET,
            detailUrl,
            {
                    response ->
                Log.d("result", response.toString())
                val result = JSONObject(response)
                val type = result.getJSONArray("types")
                var types: ArrayList<String> = ArrayList<String>()

                for(i in 0 until type.length()){
                    types.add(type.getJSONObject(i).getJSONObject("type").getString("name"))
                }

                val weight = result.getDouble("weight")
                val height = result.getDouble("height")

                // 띄우기
                Glide.with(this@DetailActivity).load(pokemonImgPath).into(iv_img)
                tv_name.text = pokemonNm
                tv_weight.text = weight.toString() + "kg"
                tv_height.text = height.toString() + "m"
                if(types.size == 1){
                    tv_type1.text = types.get(0)
                    tv_type2.visibility = View.GONE
                    tv_type2_title.visibility = View.GONE
                }else{
                    tv_type1.text = types.get(0)
                    tv_type2.text = types.get(1)
                }

            },
            {
                    error ->
                Log.d("error", error.toString())
                Toast.makeText(this, "error발생", Toast.LENGTH_SHORT).show()
            }
        ){}
        reqQueue.add(request)
        // response -> JSONObject -> result
        // type = result.getJSONArray("types")
        // type.getJSONObject(index).getJSONObject("type").getString("name")
        //                    index -> 0,1 (1번째 없을수도 있음)

        // result.getDouble("weight")
        // result.getDouble("height")





    }
}