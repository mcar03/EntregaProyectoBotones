package com.example.pruebamartin.controller

import android.content.Context
import com.example.pruebamartin.MainActivity
import com.example.pruebamartin.MotosActivity
import com.example.pruebamartin.adapter.AdapterMotos
import com.example.pruebamartin.dao.DaoMotos
import com.example.pruebamartin.models.Motos

class Controller(val context : Context) {
    lateinit var listaMotos: MutableList<Motos>

    init {
        initData()
    }

    private fun initData() {
        listaMotos = DaoMotos.myDao.getDataMotos().toMutableList()
    }

    fun setAdapter(){
        val myActivity = context as MotosActivity
        myActivity.binding.myRecyclerView.adapter = AdapterMotos(listaMotos)
    }
}