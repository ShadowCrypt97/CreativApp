package com.proyecto.appejemplomascotas

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UsuarioEntidad::class,MascotaEntity::class],
    version = 1
)
abstract class bdUsuarios:RoomDatabase() {

    abstract fun daoUsuario():UsuarioDAO

    abstract fun daoMascota():MascotaDAO
}