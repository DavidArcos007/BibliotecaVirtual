package dev.android.appreservas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.room.Database
import androidx.room.RoomDatabase
import kotlinx.android.synthetic.main.activity_detalle_menu.*
import kotlinx.android.synthetic.main.activity_nuevo_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalleMenuActivity : AppCompatActivity() {
    private lateinit var database: AppDatabase
    private lateinit var menu: dev.android.appreservas.clases.Menu
    private lateinit var menuLiveData: LiveData<dev.android.appreservas.clases.Menu>
    private val EDITAR = 2
    private val SELECCIONAR = 2
    private var uriImagen: Uri?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_menu)
        cargarMenu()
        cargar_imagen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == SELECCIONAR && resultCode == Activity.RESULT_OK ->{
                uriImagen = data!!.data
                imgDetalleMenu.setImageURI(uriImagen)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.acciones_menu, menu)
        return true
    }

    private fun cargar_imagen(){
        imgDetalleMenu.setOnClickListener(){
            ControladorImagen.seleccionarFotoGaleria(this, SELECCIONAR)
        }
    }

    private fun cargarMenu(){
        database = AppDatabase.getDatabase(this)
        val idMenu = intent.getIntExtra("id", 0)

        menuLiveData = database.menus().obtenerMenu(idMenu)
        menuLiveData.observe(this, Observer {
            menu = it

            txtDetalleMenuNombre.setText(menu.nombre)
            txtDetalleMenuDetalle.setText(menu.detalle)
            txtDetalleMenuPrecio.setText(menu.precio.toString())

            val uriImagen = ControladorImagen.obtenerUriImagen(this,idMenu.toLong())
            imgDetalleMenu.setImageURI(uriImagen)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.opEditarMenu -> {
                editarMenu()
                true
            }

            R.id.opEliminarMenu -> {
                eliminarMenu()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun editarMenu(){
        database = AppDatabase.getDatabase(this)

        val nombre = txtDetalleMenuNombre.text.toString()
        val detalle = txtDetalleMenuDetalle.text.toString()
        val precio = txtDetalleMenuPrecio.text.toString()

        menu.nombre = nombre
        menu.detalle = detalle
        menu.precio = precio.toDouble()

        var idMenu = menu.idMenu

        CoroutineScope(Dispatchers.IO).launch {
            database.menus().actualizarMenu(menu)

            uriImagen?.let {
                ControladorImagen.guardarImagen(this@DetalleMenuActivity,idMenu.toLong(),it)
            }
            this@DetalleMenuActivity.finish()
        }
    }

    private fun eliminarMenu() {
        menuLiveData.removeObservers(this)
        CoroutineScope(Dispatchers.IO).launch {
            database.menus().eliminarMenu(menu)
            this@DetalleMenuActivity.finish()
        }
    }
}