package com.example.manaforge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.manaforge.utilidades.Constants
import com.example.testretrofit.model.card.CardList
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

class BuscadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscador)
        //politicas para poder acceder a internet
        val policy = StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)

        //listener del boton buscar
        val buttonBuscar = findViewById<Button>(R.id.buttonBuscar)
        val editTextNombreCarta = findViewById<EditText>(R.id.editTextTextNombreCarta)

        buttonBuscar.setOnClickListener {
            if (!editTextNombreCarta.text.isNullOrEmpty()){
                var url = Constants.URL_BASE_GET_CARD_ENG + formatNameCardUrl(editTextNombreCarta.text.toString())
                try {
                    var cardList = getCard(url)

                    if (cardList != null) {
                        println(cardList.toString())
                        if(cardList.data.size>1){
                            var intentResultadoBuscadorLista = Intent(applicationContext,ListViewActivity::class.java)
                            intentResultadoBuscadorLista.putExtra("cartas" , cardList)
                            startActivity(intentResultadoBuscadorLista)
                        }else if (cardList.data.size == 1){
                            var intentResultadoBuscador = Intent(applicationContext,ResultadoBuscadorActivity::class.java)
                            intentResultadoBuscador.putExtra("carta" , cardList.data.get(0))
                            startActivity(intentResultadoBuscador)
                        }

                    }
                }catch (exceptio:IOException){
                    Toast.makeText(applicationContext,"No se ha encontrado la carta",Toast.LENGTH_LONG).show()
                }

            }

        }
    }


    fun getCard(url:String): CardList? {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        Toast.makeText(applicationContext,"Buscando",Toast.LENGTH_SHORT).show()
        var respuesta = ""

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            respuesta = response.body()?.string().toString()
            println(respuesta)
        }


        println(respuesta)
        return Gson().fromJson(respuesta, CardList::class.java)

    }
    fun formatNameCardUrl(nombre:String):String{

        var nombreSplited = nombre.split(" ")
        var nombreFormated = ""
        for (word in nombreSplited){
            if (word != nombreSplited.last()) {
                nombreFormated = nombreFormated + word +"+"
            }else{
                nombreFormated = nombreFormated + word
            }

        }
        return  nombreFormated
    }
}