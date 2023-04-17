package com.example.manaforge


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Transformations
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.manaforge.model.Card
import okhttp3.OkHttpClient

class CardArrayAdapter(context: Context,viewToPaint:Int, private val cartas: ArrayList<Card>)
    :ArrayAdapter <Card>(context,viewToPaint,cartas){
    private val client = OkHttpClient()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {

       //falta por eliminar de cartas las que no tengan los datos necesarios

        val inflater = LayoutInflater.from(context)
        val currentListItem = inflater.inflate(R.layout.itemlistcardbuscador,null)
        if(cartas.get(position)!=null && cartas.get(position).image_uris!=null && cartas.get(position).image_uris.normal!=null){

        val imageview = currentListItem.findViewById<ImageView>(R.id.imageViewCard)
        val textViewName = currentListItem.findViewById<TextView>(R.id.textViewName)
        //val textViewSet = currentListItem.findViewById<TextView>(R.id.textViewSet)
            if (!cartas.get(position).printed_name.isNullOrEmpty()){
                textViewName.text = cartas.get(position).printed_name
            }else{
                textViewName.text = cartas.get(position).name
            }


            //textViewSet.text = cartas.get(position).set_name




    val options: RequestOptions = RequestOptions()
        .placeholder(R.drawable.defaultt) // Imagen de carga mientras se carga la imagen real
        .error(R.drawable.error) // Imagen a mostrar en caso de error de carga
        .diskCacheStrategy(DiskCacheStrategy.ALL) // Guardar la imagen en caché


    Glide.with(context)
        .load(cartas.get(position).image_uris.large)
        .apply(options)
        .apply(RequestOptions.bitmapTransform(RoundedCorners(10)))
        .into(imageview)
    Log.i("[CardArrayAdapter]" ,"***** Pintando la carta: " + cartas.get(position).printed_name)

}


        return currentListItem
    }

}