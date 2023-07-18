package com.orthh.ex230718

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MovieActivity : AppCompatActivity() {

    lateinit var tvMovie:TextView
    lateinit var btnMovie: Button
    lateinit var lvMovieResult: ListView

    lateinit var reqQueue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        // component 가져오기
        tvMovie = findViewById(R.id.tvMovie)
        btnMovie = findViewById(R.id.btnMovie)
        lvMovieResult = findViewById(R.id.lvMovieResult)
        // RequestQueue 생성
        reqQueue = Volley.newRequestQueue(this@MovieActivity)
        // 버튼이 클릭되면 영화api를 통해 20230717 영화 순위 데이터 요청 후 응답값(순위, 영화제목, 개봉날짜 -> MovieVO(data class))처리

        val movieList = ArrayList<MovieVO>()

        btnMovie.setOnClickListener {
            val request = StringRequest(
                Request.Method.GET,
                "https://kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=f5eef3421c602c6cb7ea224104795888&targetDt=20230717",
                {
                    response ->Log.d("response", response.toString())
                    val result = JSONObject(response)
                    val dailyBoxOfficeList = result.getJSONObject("boxOfficeResult").getJSONArray("dailyBoxOfficeList")
                    for(i in 0 until dailyBoxOfficeList.length()){
                        var movie = dailyBoxOfficeList.get(i) as JSONObject
                        var rank = movie.getString("rank")
                        var movieNm = movie.getString("movieNm")
                        var openDt = movie.getString("openDt")
                        movieList.add(MovieVO(rank, movieNm, openDt))
                    }
                    val adapter = ArrayAdapter<MovieVO>(applicationContext, android.R.layout.simple_list_item_1, movieList)

                    lvMovieResult.adapter = adapter
                },
                {
                        error -> Log.d("error", error.toString())
                    Toast.makeText(this, "error발생!", Toast.LENGTH_SHORT).show()
                }


                )
            reqQueue.add(request)
        }
        // 요청은 전체 데이터 가져오기

    }
}