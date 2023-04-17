package com.example.manaforge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.example.manaforge.model.Card
import com.example.testretrofit.model.card.CardList

class ListViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)
        val cardListView= findViewById<ListView>(R.id.listaCartas)
        val intent = intent
        val cartas = intent.getSerializableExtra("cartas") as CardList
        var cardArrayAdapter= CardArrayAdapter(applicationContext,R.layout.itemlistcardbuscador,cartas.data)
        cardListView.adapter = cardArrayAdapter

        cardListView.setOnItemClickListener { adapterView, view, position, id ->

            var currentCard = cartas.data.get(position)
            var i = Intent(applicationContext,ResultadoBuscadorActivity::class.java)
            i.putExtra("carta",currentCard)
            startActivity(i)
        }
    }

}