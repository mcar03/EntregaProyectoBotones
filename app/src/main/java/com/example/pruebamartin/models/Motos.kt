package com.example.pruebamartin.models

class Motos(
    var nombre: String,
    var annoMoto: Int,
    var precio: Double,
    var image: String
) {
    override fun toString(): String {
        return "Motos(nombew='$nombre', Año moto='$annoMoto', precio='$precio', image='$image')"
    }
}
