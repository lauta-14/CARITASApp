package com.example.caritas.ui.registro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.caritas.R
import com.example.caritas.databinding.FragmentRegistroBinding
import com.example.caritas.ui.login.LoginViewModel
import modelo.usuario.Usuario

class RegistroFragment : Fragment() {
    private lateinit var mViewModel: LoginViewModel
    private var _binding: FragmentRegistroBinding? = null
    private val binding get() = _binding!!
    private lateinit var nombreUsuario: EditText
    private lateinit var passUsuario: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegistroBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        nombreUsuario = binding.registroUsername
        passUsuario = binding.registroPass

        binding.registroBtn.setOnClickListener {
            if (validar()) {
                val usuario = Usuario(
                    nombre = nombreUsuario.text.toString(),
                    pass = passUsuario.text.toString()
                )
                guardarUsuario(usuario)
                NavHostFragment.findNavController(this@RegistroFragment)
                    .navigate(R.id.action_registroFragment_to_loginFragment)
            }
        }
        mostrarUsuarios()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun guardarUsuario(usuario: Usuario) {
        mViewModel.insertarUsuario(usuario)
        Toast.makeText(requireContext(), "Usuario guardado correctamente", Toast.LENGTH_SHORT).show()
    }

    private fun validar(): Boolean {
        var resultado = true
        if (nombreUsuario.text.toString().isEmpty()) {
            resultado = false
            nombreUsuario.error = "ingresar nombre"
        } else if (passUsuario.text.toString().isEmpty()) {
            resultado = false
            passUsuario.error = "ingresar pass"
        }
        return resultado
    }

    private fun mostrarUsuarios() {
        mViewModel.getUsuarios().observe(viewLifecycleOwner) { usuarios ->
            usuarios.forEach { usuario ->
                Log.e("Nombre usuario: ", usuario.nombre)
            }
        }
    }
}