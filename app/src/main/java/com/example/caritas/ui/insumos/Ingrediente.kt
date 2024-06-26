package com.example.caritas.ui.insumos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caritas.R

data class Ingrediente(val nombre: String, val cantidad: String, val precioUnitario: Double)

class IngredienteAdapter(private val ingredientes: List<Ingrediente>) :
    RecyclerView.Adapter<IngredienteAdapter.IngredienteViewHolder>() {

    class IngredienteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombre: TextView = itemView.findViewById(R.id.textViewNombreIngrediente)
        val textViewCantidad: TextView = itemView.findViewById(R.id.textViewCantidadIngrediente)
        val textViewPrecio: TextView = itemView.findViewById(R.id.textViewPrecioIngrediente)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredienteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_ingrediente, parent, false)
        return IngredienteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredienteViewHolder, position: Int) {
        val ingrediente = ingredientes[position]
        holder.textViewNombre.text = ingrediente.nombre
        holder.textViewCantidad.text = ingrediente.cantidad
        holder.textViewPrecio.text = String.format("$%.2f", ingrediente.precioUnitario)
    }

    override fun getItemCount() = ingredientes.size
}
