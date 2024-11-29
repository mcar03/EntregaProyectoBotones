package com.example.pruebamartin.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamartin.databinding.ActivityItemBinding
import com.example.pruebamartin.models.Motos
import com.bumptech.glide.Glide

class ViewHMotos(private val binding: ActivityItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun renderize(motos: Motos, position: Int, deleteListener: (Int) -> Unit) {
        // Establece el nombre, el año y el precio
        binding.textViewNombreMotoRecicler.text = motos.nombre
        binding.textViewAnnoRecicler.text = motos.annoMoto.toString()
        binding.textViewPrecioMotoRecicler.text = motos.precio.toString()

        // Usa Glide para cargar la imagen desde la URL
        Glide.with(itemView.context)
            .load(motos.image) // Asegúrate de que 'image' es una URL válida
            .centerCrop()
            .into(binding.imageViewFotoMoto)

        // Configura el listener para el botón de eliminar
        binding.imageViewIconoBasura.setOnClickListener {
            deleteListener(position)
        }
    }
}
