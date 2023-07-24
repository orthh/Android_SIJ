package com.orthh.pokemon

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PokemonViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    lateinit var ivPoket: ImageView
    lateinit var tvPoket : TextView

    init{ // component 초기화
        ivPoket = itemView.findViewById(R.id.ivPokemon)
        tvPoket = itemView.findViewById(R.id.tvPokemon)
    }

}