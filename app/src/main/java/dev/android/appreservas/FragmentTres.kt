package dev.android.appreservas

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText


class FragmentTres : Fragment() {

    interface  ComunicadorFragmentos {
        fun enviarDatos(dato:String)

    }

    private var activityContenedora:ComunicadorFragmentos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tres, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ComunicadorFragmentos)
            activityContenedora = context
        else throw RuntimeException(
            context.toString() +
                    "Debe implementarse la interfaz ComunicadorFragmentos "
        )
    }

    override fun onDetach() {
        super.onDetach()
        activityContenedora = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val btn : Button = requireView().findViewById(R.id.btnFrag3)
        btn.setOnClickListener{
            val et: EditText = requireView().findViewById(R.id.txtFrag3)
            activityContenedora!!.enviarDatos(et.text.toString())
        }
    }
}