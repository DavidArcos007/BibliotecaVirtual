package dev.android.appreservas.clases

import android.content.Context

class Preferencias(val context: Context) {
    val SHARED_APP = "MyApp"
    val SHARED_ESTADO = "estado"
    val SHARED_NOMBRE = "nombre"
    val USER = "usuario"
    val AUX = ""

    val almacenamiento = context.getSharedPreferences(SHARED_APP, Context.MODE_PRIVATE)

    fun guardarEstado(estado: Boolean) {
        almacenamiento.edit().putBoolean(SHARED_ESTADO, estado).apply()
    }

    fun devolverEstado(): Boolean {
        return almacenamiento.getBoolean(SHARED_ESTADO, false)
    }
    fun guardarUsuario( user:String ){
        almacenamiento.edit().putString(USER, user).apply()
    }

    fun recuperarUsuario():String?{
        var datosU: String? = almacenamiento.getString( USER ,"" )
        return datosU
    }
    private fun revisarEstado():Boolean{
        return AppReservas.preferencias.devolverEstado()
    }


}