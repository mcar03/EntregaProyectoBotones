package com.example.pruebamartin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.pruebamartin.databinding.ActivityItemBinding
import com.example.pruebamartin.models.Motos

class AdapterMotos (var listaMotos:MutableList<Motos>) : RecyclerView.Adapter<ViewHMotos>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHMotos {
        val binding = ActivityItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHMotos(binding)
    }

    override fun getItemCount(): Int = listaMotos.size

    override fun onBindViewHolder(holder: ViewHMotos, position: Int) {
       holder.renderize(listaMotos[position])
    }


}