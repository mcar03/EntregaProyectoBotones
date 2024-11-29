package com.example.pruebamartin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pruebamartin.databinding.ActivityItemBinding
import com.example.pruebamartin.models.Motos

class AdapterMotos(
    private val context: Context,
    var listaMotos: MutableList<Motos>,
    private val deleteListener: (Int) -> Unit
) : RecyclerView.Adapter<AdapterMotos.ViewHMotos>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHMotos {
        val binding = ActivityItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHMotos(binding)
    }

    override fun getItemCount(): Int = listaMotos.size

    override fun onBindViewHolder(holder: ViewHMotos, position: Int) {
        holder.renderize(listaMotos[position], position, deleteListener)  // Pasa el deleteListener aquí
    }

    // Método para eliminar un ítem
    fun deleteItem(position: Int) {
        listaMotos.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, itemCount)
    }

    // ViewHolder para el RecyclerView
    inner class ViewHMotos(private val binding: ActivityItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun renderize(moto: Motos, position: Int, deleteListener: (Int) -> Unit) {
            binding.textViewNombreMotoRecicler.text = moto.nombre
            binding.textViewAnnoRecicler.text = moto.annoMoto.toString()
            binding.textViewPrecioMotoRecicler.text = moto.precio.toString()
            Glide.with(itemView.context).load(moto.image).centerCrop().into(binding.imageViewFotoMoto)

            // Configuramos el listener del botón de eliminar
            binding.imageViewIconoBasura.setOnClickListener {
                // Mostrar el AlertDialog cuando se hace clic en el botón de borrar
                val builder = AlertDialog.Builder(context)
                builder.setMessage("¿Estás seguro de que quieres borrar este ítem?")
                    .setCancelable(false)
                    .setPositiveButton("Sí") { dialog, id ->
                        // Si el usuario acepta, se borra el ítem
                        deleteListener(position) // Llamamos a la función que borra el ítem
                        Toast.makeText(context, "Ítem borrado", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("No") { dialog, id ->
                        // Si el usuario cancela, no hace nada
                        dialog.cancel()
                    }
                val alert = builder.create()
                alert.show()
            }
        }
    }
}
