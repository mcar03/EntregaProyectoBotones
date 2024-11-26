package com.example.pruebamartin.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pruebamartin.databinding.ActivityItemBinding
import com.example.pruebamartin.models.Motos
import com.bumptech.glide.Glide

class ViewHMotos(private val binding:ActivityItemBinding): RecyclerView.ViewHolder(binding.root) {

    fun renderize(motos: Motos){
        binding.textViewNombreMotoRecicler.text = motos.nombre
        binding.textViewAnnoRecicler.text = motos.annoMoto.toString()
        binding.textViewPrecioMotoRecicler.text = motos.precio.toString()
        Glide.with(itemView.context).load(motos.image).centerCrop().into(binding.imageViewFotoMoto)
    }
}