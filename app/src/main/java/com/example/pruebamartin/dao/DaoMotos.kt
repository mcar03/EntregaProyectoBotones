package com.example.pruebamartin.dao

import com.example.pruebamartin.interfaces.InterfaceMotos
import com.example.pruebamartin.models.Motos
import com.example.pruebamartin.objects_models.Repository

class DaoMotos private constructor(): InterfaceMotos{
    companion object{
        val myDao : DaoMotos by lazy {
            DaoMotos()
        }
    }
    override fun getDataMotos(): List<Motos> = Repository.listaMotos
}
