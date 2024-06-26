package com.example.caritas.ui.confirmar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.caritas.R

// ConfirmarCompraFragment.kt
class ConfirmarCompraFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_confirmar_compra, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val costoTotalTextView: TextView = view.findViewById(R.id.costoTotalCompra)
        val proveedorSpinner: Spinner = view.findViewById(R.id.proveedorSpinner)
        val confirmarButton: Button = view.findViewById(R.id.confirmarButton)
        val cancelarButton: Button = view.findViewById(R.id.cancelarButton)

        val costoTotal = arguments?.getDouble("costoTotal") ?: 0.0
        costoTotalTextView.text = String.format("$%,.2f", costoTotal)

        // Lista de proveedores hardcodeada
        val proveedores = listOf("Carnicería Juan", "Almacén Doña María", "Verdulería La Huerta")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, proveedores)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        proveedorSpinner.adapter = adapter

        confirmarButton.setOnClickListener {
            val proveedorSeleccionado = proveedorSpinner.selectedItem.toString()
            Toast.makeText(requireContext(), "Compra confirmada con $proveedorSeleccionado", Toast.LENGTH_SHORT).show()

            // Pasar el costo total al fragmento CuentaCorrienteFragment
            val bundle = Bundle()
            bundle.putDouble("costoTotal", costoTotal)
            findNavController().navigate(R.id.action_confirmarCompraFragment_to_cuentaCorrienteFragment, bundle)
        }

        cancelarButton.setOnClickListener {
            findNavController().navigate(R.id.action_confirmarCompraFragment_to_elegirComidasFragment)
        }
    }
}

