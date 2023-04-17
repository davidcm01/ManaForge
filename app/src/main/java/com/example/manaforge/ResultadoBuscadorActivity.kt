package com.example.manaforge

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.manaforge.model.Card
import com.example.manaforge.model.CardSet
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class ResultadoBuscadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_buscador)

        //politicas para poder acceder a internet
        val policy = StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val intent = intent
        val carta = intent.getSerializableExtra("carta") as Card

            val cardSet = getCardSet(carta.set_uri)



        val nombreCarta = findViewById<TextView>(R.id.textViewNombreCarta)

        if (!carta.printed_name.isNullOrEmpty()){
            nombreCarta.text = carta.printed_name
        }else{
            nombreCarta.text = carta.name
        }
        val nombreSet = findViewById<TextView>(R.id.textViewNameSet)
            nombreSet.text = carta.set_name

        val textViewTipo = findViewById<TextView>(R.id.textViewTipo)
        if (!carta.printed_type_line.isNullOrEmpty()){
            textViewTipo.text = carta.printed_type_line
        }else{
            textViewTipo.text = carta.type_line
        }

        val cardText = findViewById<TextView>(R.id.textViewTextCard)
        if (!carta.printed_text.isNullOrEmpty()){
            cardText.text = carta.printed_text
        }else{
            cardText.text = carta.oracle_text
        }

        val buttonGatherer = findViewById<Button>(R.id.buttonGatherer)
        buttonGatherer.setOnClickListener {
            if(carta.related_uris.gatherer != null){
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(carta.related_uris.gatherer)
                startActivity(intent)
            }

        }

        val buttonCardmarket = findViewById<Button>(R.id.buttonCardmarket)
        buttonCardmarket.setOnClickListener {
            if(carta.purchase_uris.cardmarket != null){
                val intent = Intent(Intent.ACTION_VIEW)
                intent.data = Uri.parse(carta.purchase_uris.cardmarket)
                startActivity(intent)
            }

        }

        loadCardImage(carta.image_uris.large)
        loadCardSetImage(cardSet.icon_svg_uri)
    }

    fun loadCardImage(urlImage:String){
        // Image view
        val imageView = findViewById<ImageView>(R.id.imageViewCarta)

        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.defaultt) // Imagen de carga mientras se carga la imagen real
            .error(R.drawable.error) // Imagen a mostrar en caso de error de carga
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Guardar la imagen en caché


        Glide.with(applicationContext)
            .load(urlImage)
            .apply(options)
            .apply(RequestOptions.bitmapTransform(RoundedCorners(37)))
            .into(imageView)
    }
    fun loadCardSetImage(urlImage:String){

    }

    fun getCardSet(url:String): CardSet {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()

        Toast.makeText(applicationContext,"Buscando", Toast.LENGTH_SHORT).show()
        var respuesta = ""

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")

            respuesta = response.body()?.string().toString()
            println(respuesta)
        }

        return Gson().fromJson(respuesta, CardSet::class.java)

    }
}