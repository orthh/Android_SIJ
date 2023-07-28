package com.orthh.ex20230720

data class KakaoVO(var imgId: Int? = 0, var name:String? = "", var msg: String? = "", var time: String? = ""){
    //                  imgId가 null 이면 0으로 설정
    // ChildEvent 클래스에서 Datasnapshot 을 KakaoVO로 형변환하고 있음.
    // => getValue(KakaoVo::class.java)
    // java버전 => getValue(KakaoVO.class)
    // 중요! 형변환시 반드시 기본생성자가 정의되어 있어야함!
    // 생성자는 객체를 생성할 때 함께 호출되는 메소드!
    // ex) new KakaoVO()
    // ex ) new KakaoVO(1, "a", "b", "c", "d") 
    // kotlin 에서 기본 생성자 만드는 방법 ? 매개변수에 null 값을 허용해주면 됨.


}