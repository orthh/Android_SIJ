package com.orthh.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import android.widget.EditText

class Fragment2 : Fragment() {
    // View 생성(**)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_2, container, false)

        // component 가져오기
        var etUrl:EditText = view.findViewById(R.id.etUrl)
        var btnUrl: Button = view.findViewById(R.id.btnUrl)
        // 버튼을 클릭하면 사용자가 작성한 url 값 가져오기
        btnUrl.setOnClickListener { 
            // 버튼을 클릭하면 사용자가 작성한 url 값 가져오기
            val url:String = etUrl.text.toString()
            // url 값 저장(SharedPreference -> 내부 메모리)
            val spf: SharedPreferences = requireActivity().getSharedPreferences("mySPF", Context.MODE_PRIVATE)
            // MODE_PRIVATE : 내부 캐시에 저장 -> 노출 X
            // - editor 사용
            val editor: SharedPreferences.Editor = spf.edit()
            editor.putString("url", url)
            editor.commit()
        }

        

        return view
    }
}