package com.example.caritas.ui.comidas

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.caritas.R

class ComidaAdapter(
    private val comidas: List<Plato>, // Ahora recibe una lista de objetos Plato
    private val onClickListener: (String) -> Unit
) : RecyclerView.Adapter<ComidaAdapter.ComidaViewHolder>() {

    class ComidaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewNombreComida: TextView = itemView.findViewById(R.id.textViewNombreComida)
        val imageViewComida: ImageView = itemView.findViewById(R.id.imageViewComida) // Agregar esta línea
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comida, parent, false)
        return ComidaViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComidaViewHolder, position: Int) {
        val plato = comidas[position]
        holder.textViewNombreComida.text = plato.nombre
        holder.imageViewComida.setImageResource(plato.imagenResId) // Mostrar la imagen
        holder.itemView.setOnClickListener { onClickListener(plato.nombre) }
    }

    override fun getItemCount() = comidas.size
}
