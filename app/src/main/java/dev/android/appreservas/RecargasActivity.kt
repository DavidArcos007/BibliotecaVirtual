package dev.android.appreservas

import android.content.Context
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_menu.*
import kotlinx.android.synthetic.main.activity_recargas.*

data class MenuRecargas(var nombre:String, var detalle:String)


class RecargasActivity : AppCompatActivity() {

    val menuRecargas = arrayListOf(
        MenuRecargas("Recarga Simple", "5 Dolares"),
        MenuRecargas("Recarga Normal", "20 Dolares"),
        MenuRecargas("Recarga Super", "50 Dolares")
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recargas)
        crearLista()
    }

    private fun crearLista(){
        val adaptador = Adaptador(applicationContext,menuRecargas)
        lstRecargas.adapter = adaptador
    }

    private class Adaptador(context: Context, datos:ArrayList<MenuRecargas>):BaseAdapter()
    {
        val datosMenu = datos
        val ctx = context

        private inner class  ViewHolder()
        {
        internal var nombre: TextView?= null
            internal var detalle: TextView?= null
            internal var flecha: ImageView?= null
        }

        override fun getCount(): Int {
            return datosMenu.size
        }

        override fun getItem(position: Int): Any {
            return datosMenu[position]
        }

        override fun getItemId(position: Int): Long {
            return  position.toLong()
        }

        override fun getView(position: Int, row: View?, parent: ViewGroup?): View {
            var viewHolder:ViewHolder
            var rowView = row

            if (rowView==null){
                viewHolder = ViewHolder()
                val inflater = LayoutInflater.from(ctx)
                rowView = inflater.inflate(R.layout.fila_personalizada, parent, false)

                viewHolder.nombre = rowView.findViewById(R.id.txtNombre) as TextView
                viewHolder.detalle = rowView.findViewById(R.id.txtDetalle) as TextView
                viewHolder.flecha = rowView.findViewById(R.id.imgFlecha) as ImageView
                rowView.tag = viewHolder

            } else {
                viewHolder = rowView.tag as ViewHolder
            }
            viewHolder.nombre!!.setText(datosMenu.get(position).nombre)
            viewHolder.detalle!!.setText(datosMenu.get(position).detalle)
            viewHolder.flecha!!.setImageResource(R.drawable.ic_flecha_derecha)

            return rowView!!
        }

    }
}