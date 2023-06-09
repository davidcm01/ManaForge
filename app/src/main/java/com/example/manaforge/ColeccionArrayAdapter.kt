package com.example.manaforge

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.manaforge.model.firebase.Coleccion

class ColeccionArrayAdapter(context: Context, viewToPaint:Int, private val colecciones: ArrayList<Coleccion>)
    : ArrayAdapter<Coleccion>(context,viewToPaint,colecciones){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        //falta por eliminar de cartas las que no tengan los datos necesarios

        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.itemlistcoleccion,null)

            val textViewName = currentListItem.findViewById<TextView>(R.id.textViewNombreColeccion)
            textViewName.text = colecciones.get(position).nombre

        return currentListItem
    }
}