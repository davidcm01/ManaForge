package com.example.manaforge

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.manaforge.model.Card
import org.w3c.dom.Text
import java.util.concurrent.Executors

class ResultadoBuscadorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado_buscador)

        val intent = intent
        val carta = intent.getSerializableExtra("carta") as Card

        val nombreCarta = findViewById<TextView>(R.id.textViewNombreCarta)
        if (!carta.printed_name.isNullOrEmpty()){
            nombreCarta.text = carta.printed_name
        }else{
            nombreCarta.text = carta.name
        }

        loadImage(carta.image_uris.normal)
    }

    fun loadImage(urlImage:String){
        // Image view
        val imageView = findViewById<ImageView>(R.id.imageViewCarta)

        val options: RequestOptions = RequestOptions()
            .placeholder(R.drawable.defaultt) // Imagen de carga mientras se carga la imagen real
            .error(R.drawable.error) // Imagen a mostrar en caso de error de carga
            .diskCacheStrategy(DiskCacheStrategy.ALL) // Guardar la imagen en caché


        Glide.with(applicationContext)
            .load(urlImage)
            .apply(options)
            .into(imageView)
    }
}