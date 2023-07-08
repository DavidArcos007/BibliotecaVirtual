package dev.android.appreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.android.appreservas.clases.AppReservas.Companion.preferencias
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var usuario:String
    lateinit var contrasena:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoginClick()
        if (revisarEstado()){
            startActivity(Intent(this,OpcionesActivity::class.java))
        }
    }
    private fun btnLoginClick(){
        btnLogin.setOnClickListener {
            if( controles() ){
                preferencias.guardarEstado(btnRecuerdame.isChecked())
                if( btnRecuerdame.isChecked() == true ){
                    preferencias.guardarUsuario(edtUsername.text.toString())
                }else{
                    preferencias.guardarUsuario(" ")
                }
                val intent = Intent(this, OpcionesActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(applicationContext, "Llene los campos necesarios", Toast.LENGTH_LONG).show()
            }

        }
    }
    fun controles():Boolean{
        usuario = edtUsername.text.toString()
        contrasena = edtPassword.text.toString()
        if( usuario.equals("") || contrasena.equals("") ){
            return false
        }
        return true
    }

    private fun revisarEstado():Boolean{
        return preferencias.devolverEstado()
    }
 }
