package com.example.manaforge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import android.widget.ListView
import android.widget.Toast
import com.example.manaforge.model.Card
import com.example.testretrofit.model.card.CardList

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val cardListView= findViewById<GridView>(R.id.listaCartas)
        val intent = intent
        val cartas = intent.getSerializableExtra("cartas") as CardList
        val cartasLimpias = clearNullCards(cartas.data)
        var cardArrayAdapter= CardArrayAdapter(applicationContext,R.layout.itemlistcardbuscador,cartasLimpias)
        cardListView.adapter = cardArrayAdapter

        cardListView.setOnItemClickListener { adapterView, view, position, id ->

            var currentCard = cartasLimpias.get(position)
            var i = Intent(applicationContext,ResultadoBuscadorActivity::class.java)
            i.putExtra("carta",currentCard)
            startActivity(i)
        }
    }

    fun clearNullCards(cartas:List<Card>):ArrayList<Card>{
        var cartasLimpias:ArrayList<Card> = ArrayList()
        for( (i, carta)in cartas.withIndex()) {
            if (carta!=null && carta.image_uris!=null && carta.image_uris.normal!=null) {
                cartasLimpias.add(carta)

            }
        }
        return cartasLimpias
    }

}