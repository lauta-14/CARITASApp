package com.example.caritas.ui.cuenta

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.caritas.R

// CuentaCorrienteFragment.kt
class CuentaCorrienteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cuenta_corriente, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val saldoTextView: TextView = view.findViewById(R.id.saldoTextView)
        val costoTotal = arguments?.getDouble("costoTotal") ?: 0.0

        // Lógica para manejar el saldo (puedes usar SharedPreferences)
        val sharedPref = requireActivity().getPreferences(Context.MODE_PRIVATE)
        var saldoActual = sharedPref.getFloat("saldo", 250000f).toDouble()
        saldoActual -= costoTotal // Restar el costo total al saldo actual

        // Guardar el nuevo saldo en SharedPreferences
        with(sharedPref.edit()) {
            putFloat("saldo", saldoActual.toFloat())
            apply()
        }

        saldoTextView.text = String.format("$%,.2f", saldoActual)
    }
}
