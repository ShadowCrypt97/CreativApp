package com.proyecto.appejemplomascotas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsuarioDAO {

    @Query("select * from usuarioentidad")
    suspend fun getUsuarios():List<UsuarioEntidad>

    @Query("select * from usuarioentidad where email=:emailUser")
    suspend fun getUsuario(emailUser:String):UsuarioEntidad

    @Insert
    suspend fun almacenarUsuario(usuario:UsuarioEntidad)

    @Query("update usuarioentidad set telefono=:tel, password=:pass, nombre=:nombre, apellido=:apellido where email=:emailUser")
    suspend fun actualizarUsuario(tel:String,pass:String,nombre:String,apellido:String,emailUser: String)

    @Query("delete from usuarioentidad where email=:emailUser")
    suspend fun deleteUsuario(emailUser:String)

}