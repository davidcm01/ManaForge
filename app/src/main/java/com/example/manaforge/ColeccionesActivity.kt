package com.example.manaforge

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.ListView
import android.widget.Toast
import com.example.manaforge.model.firebase.DatosCartaColeccionFirebase
import com.example.manaforge.model.firebase.Coleccion
import com.google.firebase.firestore.FirebaseFirestore
import java.lang.Math.random
import kotlin.math.floor
import kotlin.random.Random

class ColeccionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_colecciones)
        //politicas para poder acceder a internet
        val policy = StrictMode.ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)

//        var listaCartas:ArrayList<DatosCartaColeccionFirebase> = arrayListOf(DatosCartaColeccionFirebase())
//
//        for (i in 0..10 ){
//            var id = "abc083ee-2eec-4796-9571-bd6b14980b6c"
//            var carta = DatosCartaColeccionFirebase(id,"en","prueba$i","https://cards.scryfall.io/normal/front/a/b/abc083ee-2eec-4796-9571-bd6b14980b6c.jpg?1677139019", floor((random() * 6) + 1).toInt())
//            listaCartas.add(carta)
//        }
//        listaCartas.remove(DatosCartaColeccionFirebase())
//
//        crearColeccion("prueba coleccion buena","1",listaCartas.toList())
        obtenerColecciones()

    }

    fun obtenerColecciones(){
        val db = FirebaseFirestore.getInstance()
        val coleccionesRef = db.collection("colecciones")

    // Realizar la consulta para obtener las colecciones de un usuario
            val idUsuario = "id_del_usuario" //  .whereEqualTo("idUsuario", idUsuario)
            coleccionesRef
                .get()
                .addOnSuccessListener { querySnapshot ->
                    // Recorrer los documentos
                    val colecciones = mutableListOf<Coleccion>()
                    for (document in querySnapshot) {
                        // Convertir el documento en un objeto Colleccion y agregarlo a la lista
                        val coleccion = document.toObject(Coleccion::class.java)
                        coleccion.idColeccion = document.id
                        colecciones.add(coleccion)
                    }
                    Log.d("Colecciones: ",colecciones.toString())
                    val listViewColecciones= findViewById<ListView>(R.id.listViewColecciones)
                    var coleccionesArrayAdapter= ColeccionArrayAdapter(applicationContext,R.layout.itemlistcardbuscador, colecciones as ArrayList<Coleccion>)
                    listViewColecciones.adapter = coleccionesArrayAdapter
                    listViewColecciones.setOnItemClickListener { adapterView, view, position, id ->

                        var coleccionActual = colecciones.get(position)

                        var intentCartasColeccion = Intent(applicationContext,CartasColeccionActivity::class.java)
                        intentCartasColeccion.putExtra("coleccion" , coleccionActual)
                        startActivity(intentCartasColeccion)

                        Toast.makeText(applicationContext,coleccionActual.idColeccion,Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error al obtener colecciones del usuario", e)
                }
    }

    fun crearColeccion(nombreColeccion:String,idUser:String,listaCartasColeccion:List<DatosCartaColeccionFirebase>){
        val db = FirebaseFirestore.getInstance()
        val coleccionesRef = db.collection("colecciones")


        var coleccion =Coleccion("1",nombreColeccion,idUser,listaCartasColeccion)

            coleccionesRef.add(coleccion)
                .addOnSuccessListener { documentReference ->
                    Log.d(TAG, "Colección creada con ID: ${documentReference.id}")
                    // Agregar las cartas a la colección
                    val cartas = coleccion.cartas
                    if (cartas.isNotEmpty()) {
                        val coleccionRef = coleccionesRef.document(documentReference.id)
                        coleccionRef.update("cartas", cartas)
                            .addOnSuccessListener {
                                Log.d(TAG, "Cartas agregadas a la colección con ID: ${documentReference.id}")
                            }
                            .addOnFailureListener { e ->
                                Log.w(TAG, "Error al agregar las cartas a la colección", e)
                            }
                    }
                }
                .addOnFailureListener { e ->
                    Log.w(TAG, "Error al crear colección", e)
                }

    }
}