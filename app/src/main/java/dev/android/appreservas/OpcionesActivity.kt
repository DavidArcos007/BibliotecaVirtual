package dev.android.appreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import dev.android.appreservas.clases.AppReservas.Companion.preferencias
import kotlinx.android.synthetic.main.activity_opciones.*

class OpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        ClickBotones()
        if (preferencias.devolverEstado()){
            welcome()
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_superior, menu)
        return true
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opCerrarSesion -> {
                // se borra preferencias.almacenamiento.edit().clear()
                preferencias.guardarEstado(false)
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun ClickBotones(){
        this.btnMenu.setOnClickListener(){
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }

        this.btnReservas.setOnClickListener(){
            val intent = Intent(this, ReservasActivity::class.java)
            startActivity(intent)
        }

        this.btnBusquedas.setOnClickListener(){
            val intent = Intent(this, BusquedasActivity::class.java)
            startActivity(intent)
        }

        this.btnRecargas.setOnClickListener(){
            val intent = Intent(this, RecargasActivity::class.java)
            startActivity(intent)
        }



 }
    fun welcome(){

        txtBienvenido.setText( "Bienvenido "+preferencias.recuperarUsuario() )
    }
}