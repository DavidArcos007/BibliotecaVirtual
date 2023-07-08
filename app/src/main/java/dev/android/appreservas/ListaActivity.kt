package dev.android.appreservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.android.appreservas.databinding.ActivityListaBinding

class ListaActivity : AppCompatActivity() {

    lateinit var binding: ActivityListaBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista)
    }
}