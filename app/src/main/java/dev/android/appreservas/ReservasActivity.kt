package dev.android.appreservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import dev.android.appreservas.clases.AppReservas
import dev.android.appreservas.databinding.ActivityMainBinding
import dev.android.appreservas.databinding.ActivityReservasBinding

class ReservasActivity : AppCompatActivity(), FragmentDos.ComunicadorFragmentos, FragmentTres.ComunicadorFragmentos {

    lateinit var binding: ActivityReservasBinding
    var numeroFragmento = 0

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_envios, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opCerrarSesion -> {
                // se borra preferencias.almacenamiento.edit().clear()

                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        binding =  ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.btnDos.setOnClickListener{
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = FragmentDos()
            transaccion.replace(R.id.contenedor, fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }

        binding.  btnTres.setOnClickListener{
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = FragmentTres()
            transaccion.replace(R.id.contenedor, fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }
    }

    override fun enviarDatos(dato: String) {
        binding.txtMain.setText(dato)
    }
}
