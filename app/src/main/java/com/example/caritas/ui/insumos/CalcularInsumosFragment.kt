package com.example.caritas.ui.insumos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.caritas.R
import com.example.caritas.ui.insumos.Ingrediente
import com.example.caritas.ui.insumos.IngredienteAdapter

class CalcularInsumosFragment : Fragment() {

    private lateinit var ingredientesAdapter: IngredienteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_calcular_insumos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nombrePlatoTextView: TextView = view.findViewById(R.id.textViewNombrePlato)
        val costoTotalTextView: TextView = view.findViewById(R.id.textViewCostoTotal)
        val recyclerViewIngredientes: RecyclerView = view.findViewById(R.id.recyclerViewIngredientes)
        val btnConfirmarPedido: Button = view.findViewById(R.id.btnConfirmarPedido)

        val comidaSeleccionada = arguments?.getString("comidaSeleccionada")
        nombrePlatoTextView.text = comidaSeleccionada

        val ingredientes = when (comidaSeleccionada) {
            "Fideos con salsa bolognesa" -> listOf(
                Ingrediente("Carne picada", "7 kilos", 1200.0),
                Ingrediente("Cebolla", "3 kilos", 350.0),
                Ingrediente("Morrones", "5 unidades", 800.0),
                // ... (resto de los ingredientes)
            )
            "Guiso de arroz con pollo" -> listOf(
                Ingrediente("Pollo trozado", "10 kilos", 1500.0),
                Ingrediente("Cebolla", "3 kilos", 350.0),
                Ingrediente("Morrones", "5 unidades", 800.0),
                // ... (resto de los ingredientes)
            )
            else -> emptyList()
        }

        ingredientesAdapter = IngredienteAdapter(ingredientes)
        recyclerViewIngredientes.adapter = ingredientesAdapter
        recyclerViewIngredientes.layoutManager = LinearLayoutManager(context)

        val costoTotal = ingredientes.sumOf { it.cantidad.split(" ")[0].toDouble() * it.precioUnitario }
        costoTotalTextView.text = String.format("Costo Total: $%.2f", costoTotal)

        btnConfirmarPedido.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("comidaSeleccionada", comidaSeleccionada)
            bundle.putDouble("costoTotal", costoTotal)
            findNavController().navigate(R.id.action_calcularInsumosFragment_to_confirmarCompraFragment, bundle)
        }
    }
}

