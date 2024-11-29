package com.example.pruebamartin.controller

import android.content.Context
import com.example.pruebamartin.MotosActivity
import com.example.pruebamartin.adapter.AdapterMotos
import com.example.pruebamartin.dao.DaoMotos
import com.example.pruebamartin.models.Motos

class Controller(val context: Context) {
    lateinit var listaMotos: MutableList<Motos>
    private lateinit var adapter: AdapterMotos  // Referencia al adaptador

    init {
        initData()
    }

    private fun initData() {
        listaMotos = DaoMotos.myDao.getDataMotos().toMutableList()
    }

    fun setAdapter() {
        val myActivity = context as MotosActivity
        adapter = AdapterMotos(context, listaMotos) { position ->
            // Llamamos a deleteItem del adaptador desde la referencia
            deleteItem(position)
        }
        myActivity.binding.myRecyclerView.adapter = adapter
    }

    // Método para eliminar un ítem
    private fun deleteItem(position: Int) {
        listaMotos.removeAt(position)
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, listaMotos.size)
    }
}
