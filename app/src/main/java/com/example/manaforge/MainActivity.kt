package com.example.manaforge

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val policy = StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)


        var buttonBuscador = findViewById<Button>(R.id.buttonBuscador)
        buttonBuscador.setOnClickListener {

            var intentBuscador = Intent(applicationContext,BuscadorActivity::class.java)
            startActivity(intentBuscador)

        }

        var buttonColeccion = findViewById<Button>(R.id.buttonColeccion)
        buttonColeccion.setOnClickListener {
        //TODO llevar a vista de colleciones

        }

        var buttonMazos = findViewById<Button>(R.id.buttonMazos)
        buttonMazos.setOnClickListener {
        //TODO llevar a vista de mazos

        }

    }
}