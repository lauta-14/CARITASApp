package com.example.caritas.ui.comidas

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caritas.R

class ElegirComidasFragment : Fragment() {

    private var comidaSeleccionada: String? = null
    private val comidas = listOf(
        Plato("Fideos con salsa bolognesa", R.drawable.fideos_con_salsa_bolognesa),
        Plato("Guiso de arroz con pollo", R.drawable.guiso_arroz_pollo)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_elegir_comidas, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewComidas)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ComidaAdapter(comidas) { comida ->
            comidaSeleccionada = comida
            mostrarBotonConfirmar()
        }

        val btnConfirmar: Button = view.findViewById(R.id.btn_confirmar)
        btnConfirmar.setOnClickListener {
            if (comidaSeleccionada != null) {
                val bundle = Bundle()
                bundle.putString("comidaSeleccionada", comidaSeleccionada)
                findNavController().navigate(R.id.action_elegirComidasFragment_to_calcularInsumosFragment, bundle)
            }
        }

        return view
    }

    private fun mostrarBotonConfirmar() {
        val btnConfirmar: Button = view?.findViewById(R.id.btn_confirmar) ?: return
        btnConfirmar.visibility = View.VISIBLE
    }
}

