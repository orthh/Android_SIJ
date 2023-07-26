package com.orthh.pokemon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import org.json.JSONObject

// View Holder : 한개의 아이템(1개의 카드) 뷰
// Adapter : View Holder 생성 -> 데이터 바인딩
// Activity
class MainActivity : AppCompatActivity() {

    lateinit var rcPoketmon : RecyclerView

    lateinit var reqQueue : RequestQueue

    var pokemonList = ArrayList<PokemonVO>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        rcPoketmon = findViewById(R.id.rcPokemon)
        
        reqQueue = Volley.newRequestQueue(this@MainActivity)
        
        // 1세대 -> 151번
        for(id in 1 until 152){
            val imgUrl = "https:/raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"
            val dataUrl = "https://pokeapi.co/api/v2/pokemon-species/$id"

            val request = object : StringRequest(
                Request.Method.GET,
                dataUrl,
                {
                    response ->
                    val result = JSONObject(response)
                    val name: String = result.getJSONArray("names").getJSONObject(2).getString("name")
                    val pokemon = PokemonVO(id, imgUrl, name)
                    pokemonList.add(pokemon)


                    val adapter: PokemonAdapter = PokemonAdapter(pokemonList, applicationContext)
                    rcPoketmon.layoutManager = GridLayoutManager(applicationContext, 2 )
                    rcPoketmon.adapter = adapter
                },
                {
                    error ->
                    Log.d("error", error.toString())
                    Toast.makeText(this, "error발생", Toast.LENGTH_SHORT).show()
                }
            ){}
            reqQueue.add(request)

        }

    }
}