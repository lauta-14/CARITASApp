package com.example.caritas.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import modelo.usuario.Repositorio
import modelo.usuario.Usuario

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val repositorio: Repositorio = Repositorio(application)
    private val usuarios: LiveData<List<Usuario>> = repositorio.allUsuarios

    fun getUsuarios(): LiveData<List<Usuario>> {
        return usuarios
    }

    fun insertarUsuario(usuario: Usuario) {
        repositorio.insertUsuario(usuario)
    }

    fun getUsuarioPorNombre(nombre: String): LiveData<List<Usuario>> {
        return repositorio.getUsuarioPorNombre(nombre)
    }
}