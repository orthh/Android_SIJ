package com.orthh.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient

class Fragment1 : Fragment() {
    // View 생성(**)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view: View = inflater.inflate(R.layout.fragment_1, container, false)

        // WebView Component 가져오기
        var wv: WebView = view.findViewById(R.id.wv)

        // SharedPreferences 생성
        var spf = requireActivity().getSharedPreferences(
            "mySPF",
            Context.MODE_PRIVATE
        )
        
        
        
        // 보여주고 싶은 web url 지정
        //val url = "https://smhrd.or.kr/"
        // ++ sharedPreference 안에 저장된 url 꺼내서 사용
        // 가지고 오고 싶은 값의 key, default값
        var url = spf.getString("url","https://www.google.com")
        
        // web setting
        val ws: WebSettings = wv.settings
        // 1. javascript 사용 가능하도록 허용
        ws.javaScriptEnabled = true

        // WebView 클라이언트 설정
        wv.webViewClient = WebViewClient()
        // WebView에 url 적용
        // url? -> null 값 허용하겠다
        // url!! -> null 값 이 들어오지 않는다!
        wv.loadUrl(url!!)

        return view
    }
}