package com.example.caritas.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.caritas.MainActivity
import com.example.caritas.databinding.FragmentHomeBinding
import com.example.caritas.ui.home.HomeViewModel
import androidx.navigation.fragment.findNavController
import com.example.caritas.R

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root = binding.root

        // Llamar al método mostrarNavigation() de MainActivity
        (requireActivity() as MainActivity).mostrarNavigation()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configurar el OnClickListener para la CardView
        binding.cardElegirComidas.setOnClickListener {
            // Navegar al fragmento de "Elegir Comidas"
            findNavController().navigate(R.id.action_nav_home_to_elegirComidasFragment)
        }
        binding.cardElegirComidas.setOnClickListener {
            // Navegar al fragmento de "Calcular Insumos"
            findNavController().navigate(R.id.action_nav_home_to_calcularInsumosFragment)
        }
        binding.cardElegirComidas.setOnClickListener {
            // Navegar al fragmento de "Cuenta Corriente"
            findNavController().navigate(R.id.action_nav_home_to_cuentaCorrienteFragment)
        }
        binding.cardElegirComidas.setOnClickListener {
            // Navegar al fragmento de "Confirmar Compra"
            findNavController().navigate(R.id.action_nav_home_to_confirmarCompraFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
