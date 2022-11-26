package com.proyecto.appejemplomascotas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class MascotaEntity {

    @PrimaryKey
    var id:String
    var nombre:String
    var tipoMascota:String
    var edad:String
    var fotoUrl:Int

    constructor(id: String, nombre: String, tipoMascota: String, edad: String, fotoUrl: Int) {
        this.id = id
        this.nombre = nombre
        this.tipoMascota = tipoMascota
        this.edad = edad
        this.fotoUrl = fotoUrl
    }
}
