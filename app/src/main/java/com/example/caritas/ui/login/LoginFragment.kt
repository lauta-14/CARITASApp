package com.example.caritas.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.caritas.R
import com.example.caritas.databinding.FragmentLoginBinding
import com.example.caritas.ui.login.LoginViewModel
import modelo.usuario.Usuario

class LoginFragment : Fragment() {
    private lateinit var mViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var nombreEditext: EditText
    private lateinit var passEditext: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        nombreEditext = binding.usernameInput
        passEditext = binding.passInputInput

        binding.loginBtn.setOnClickListener {
            if (validar()) {
                val usuarioPorNombre: LiveData<List<Usuario>> = mViewModel.getUsuarioPorNombre(nombreEditext.text.toString())
                usuarioPorNombre.observe(viewLifecycleOwner) { usuarios ->
                    if (usuarios != null && usuarios.isNotEmpty()) {
                        NavHostFragment.findNavController(this@LoginFragment)
                            .navigate(R.id.action_loginFragment_to_nav_home)
                    } else {
                        Toast.makeText(requireContext(), "No existe usuario", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        binding.irRegistroBtn.setOnClickListener {
            NavHostFragment.findNavController(this@LoginFragment)
                .navigate(R.id.action_loginFragment_to_registroFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validar(): Boolean {
        var resultado = true
        if (nombreEditext.text.toString().isEmpty()) {
            resultado = false
            nombreEditext.error = "ingresar nombre"
        } else if (passEditext.text.toString().isEmpty()) {
            resultado = false
            passEditext.error = "ingresar pass"
        }
        return resultado
    }
}