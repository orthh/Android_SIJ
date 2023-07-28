package com.orthh.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.orthh.fragment.vo.BoardVO
import org.json.JSONArray

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment3.newInstance] factory method to
 * create an instance of this fragment.
 */
class Fragment3 : Fragment() {

    lateinit var reqQueue: RequestQueue
    var boardList = ArrayList<BoardVO>()
    lateinit var btnWriteAct: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_3, container, false)

        // component 가져오기
        btnWriteAct = view.findViewById(R.id.btnWriteAct)
        var rcBoard: RecyclerView = view.findViewById(R.id.rcBoard)

        btnWriteAct.setOnClickListener {
            val intent = Intent(requireActivity(), BoardWrite::class.java)
            startActivity(intent)
        }

        reqQueue = Volley.newRequestQueue(requireActivity())

        val request = object:StringRequest(
            Request.Method.GET,
            "http://172.30.1.22:8888/board",
            {
                response ->
                Log.d("response", response.toString())

                var result = JSONArray(response)
                for(i in 0 until result.length()){
                    boardList.add(Gson().fromJson(result.get(i).toString(), BoardVO::class.java))
                }
                val adapter: BoardAdapter = BoardAdapter(boardList, requireActivity())
                rcBoard.layoutManager = LinearLayoutManager(requireActivity())
                rcBoard.adapter = adapter
            },
            {
                error ->
                Log.d("error", error.toString())
            }
        ){}

        reqQueue.add(request)


        //boardList.add(BoardVO(1, "제목1", "내용1", "작성자1", "이미지경로1", 1))
        //boardList.add(BoardVO(2, "제목2", "내용2", "작성자2", "이미지경로2", 2))
        //boardList.add(BoardVO(3, "제목3", "내용3", "작성자3", "이미지경로3", 3))
        // 버튼을 클릭하면 사용자가 작성한 url 값 가져오기




        return view
    }
}