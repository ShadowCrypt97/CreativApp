package com.proyecto.appejemplomascotas

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MascotaDAO {
    @Query("select * from mascotaentity")
    suspend fun getMascotas():List<MascotaEntity>

    @Query("select * from mascotaentity where id=:idMascota")
    suspend fun getMascota(idMascota:String):MascotaEntity

    @Insert
    suspend fun almacenarMascota(mascota:MascotaEntity)

    @Query("update mascotaentity set nombre=:nombre, tipoMascota=:tipoMascota, edad=:edad, fotoUrl=:foto where id=:idMascota")
    suspend fun actualizarMascota(nombre:String,tipoMascota:String,edad:String,foto: String, idMascota:String)

    @Query("delete from mascotaentity where id=:idMascota")
    suspend fun deleteMascota(idMascota:String)
}