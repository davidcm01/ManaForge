package com.example.manaforge

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.manaforge.model.Card
import com.example.manaforge.model.CartaColeccion
import com.example.manaforge.model.firebase.DatosCartaColeccionFirebase

class CardCollectionArrayAdapter (context: Context, viewToPaint:Int, private val cartas: ArrayList<DatosCartaColeccionFirebase>)
    : ArrayAdapter<DatosCartaColeccionFirebase>(context,viewToPaint,cartas){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.itemlistcartacoleccion,null)

            val imageview = currentListItem.findViewById<ImageView>(R.id.imageViewCard)
            val textViewName = currentListItem.findViewById<TextView>(R.id.textViewName)

        textViewName.text = cartas.get(position).nombre


            val options: RequestOptions = RequestOptions()
                .placeholder(R.drawable.defaultt) // Imagen de carga mientras se carga la imagen real
                .error(R.drawable.error) // Imagen a mostrar en caso de error de carga
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Guardar la imagen en caché


            Glide.with(context)
                .load(cartas.get(position).imagen)
                .apply(options)
                .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
                .into(imageview)



        return currentListItem
    }
}