package com.example.caritas.ui.salir

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.caritas.R

class SalirFragment : Fragment(R.layout.fragment_salir) { // No es necesario un layout específico

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Lógica para cerrar sesión (por ejemplo, borrar datos de usuario)
        // ...

        // Redirigir a la pantalla de inicio de sesión (LoginFragment)
        findNavController().navigate(R.id.action_global_loginFragment)
    }
}
