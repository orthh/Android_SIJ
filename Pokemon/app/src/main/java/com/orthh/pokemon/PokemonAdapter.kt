package com.orthh.pokemon

import android.content.Context
import android.content.Intent
import android.support.v4.os.IResultReceiver._Parcel
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PokemonAdapter(var datas: ArrayList<PokemonVO>, var context: Context) : RecyclerView.Adapter<PokemonViewHolder>() {
    // ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        return PokemonViewHolder(
            // View 생성
            // attachToRoot : Root(부모) 에서 붙일건데 지금 당장 붙일거다 -> true, 이따가 붙일거다 -> false
            LayoutInflater.from(parent.context).inflate(R.layout.pokemon_item_view,parent, false)
        )
    }
    
    // 아이템(포켓몬) -> View Holder 바인딩
    // 가지고 오는 아이템 개수
    override fun getItemCount(): Int {
        return datas.size
    }
    
    // 아이템들을 ViewHolder에 바인딩
    // 아이템 -> 포켓몬 : pokemonID, pokemonImgPath, pokemonNm
    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        
        val pokemon = datas[position] // 특정 인덱스에 있는 포켓몬 데이터 가져오기
        // 포켓몬 이미지 : Glide -> HTTP 통신을 통한 이미지 로드 라이브러리
        Glide.with(context).load(pokemon.pokemonImgPath).into(holder.ivPoket)
        // 포켓몬 이름
        holder.tvPoket.text = pokemon.pokemonNm

        holder.itemView.setOnClickListener(){
            // intent 사용 -> DetailActivity 로 전환
            var intent = Intent(context, DetailActivity::class.java)
            // 안드로이드 앱에서 새로운 액티비티가 실행될 때마다 기존에 사용하던 액티비티는 스택에 쌓이게 됨
            // -> 원하지 않는 결과를 도출할 수 있어 FLAG 를 활용하여 조정 가능
            // FLAG_ACTIVITY_NEW_TASK : 새작업 시작 -> 일반적으로 Adapter 내에서 startActivity 사용 시 사용
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            // pokemonID -> Int
            // pokemonImgPath -> String
            // pokemonNm -> String

            intent.putExtra("pokemonId", datas[position].pokemonId)
            intent.putExtra("pokemonImgPath", datas[position].pokemonImgPath)
            intent.putExtra("pokemonNm", datas[position].pokemonNm)

            context.startActivity(intent)
            
        }
        
    }


}