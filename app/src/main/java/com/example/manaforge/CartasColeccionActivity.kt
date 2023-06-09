package com.example.manaforge

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.GridView
import com.example.manaforge.model.Card
import com.example.manaforge.model.CartaColeccion
import com.example.manaforge.model.Prices
import com.example.manaforge.model.firebase.Coleccion
import com.example.manaforge.model.firebase.DatosCartaColeccionFirebase
import com.example.manaforge.utilidades.Constants
import com.example.testretrofit.model.card.CardList
import com.google.android.material.tabs.TabLayout.TabGravity
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class CartasColeccionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cartas_coleccion)

        val intent = intent
        val coleccion = intent.getSerializableExtra("coleccion") as Coleccion
        val listaCartas = coleccion.cartas
        Log.i(TAG,"Cartas de la coleccion:  " + listaCartas.toString())
        val cardListView= findViewById<GridView>(R.id.listaCartas)

        var cardArrayAdapter= CardCollectionArrayAdapter(applicationContext,R.layout.itemlistcardbuscador,listaCartas as ArrayList<DatosCartaColeccionFirebase>)
        cardListView.adapter = cardArrayAdapter

        cardListView.setOnItemClickListener { adapterView, view, position, id ->

            var currentCard = listaCartas.get(position)
            var i = Intent(applicationContext,ResultadoBuscadorActivity::class.java)
            i.putExtra("carta",currentCard.id)
            startActivity(i)
        }
    }



}