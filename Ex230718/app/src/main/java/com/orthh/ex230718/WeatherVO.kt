package com.orthh.ex230718

// data class : POJO(Plain Old Java Object) -> 필드 -> getter, setter ... 자동생성
// 조건
// 1) 최소 하나의 파라미터(필드)가 있어야함
// 2) 파라미터는 val or var로 선언해야함
// 3) abstract, open(부모클래스 지정), sealed(자식클래스 제한), inner(중첩클래스) 키워드는 붙이면 안됨!
// 4) 다른 클래스를 상속받을 수 없음(sealed 클래스는 상속받을 수 있음)
data class WeatherVO (var city: String, var state: String, var temp: Int)