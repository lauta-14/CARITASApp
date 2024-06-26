package com.example.caritas.ui.cuenta

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CuentaCorrienteViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("cuenta_corriente", Context.MODE_PRIVATE)

    private val _saldo = MutableLiveData<Double>()
    val saldo: LiveData<Double> get() = _saldo

    init {
        cargarSaldoInicial()
    }

    private fun cargarSaldoInicial() {
        _saldo.value = sharedPreferences.getFloat("saldo", 250000f).toDouble()
    }

    fun actualizarSaldo(nuevoSaldo: Double) {
        with(sharedPreferences.edit()) {
            putFloat("saldo", nuevoSaldo.toFloat())
            apply()
        }
        _saldo.value = nuevoSaldo
    }
}
