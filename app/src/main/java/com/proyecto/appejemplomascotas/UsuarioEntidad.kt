package com.proyecto.appejemplomascotas

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UsuarioEntidad {

    @PrimaryKey
    var email:String
    var nombre:String
    var apellido:String
    var telefono:String
    var tipoDoc:String
    var numeroDoc:String
    var password:String

    constructor(
        email: String,
        nombre: String,
        apellido: String,
        telefono: String,
        tipoDoc: String,
        numeroDoc: String,
        password: String
    ) {
        this.email = email
        this.nombre = nombre
        this.apellido = apellido
        this.telefono = telefono
        this.tipoDoc = tipoDoc
        this.numeroDoc = numeroDoc
        this.password = password
    }
}