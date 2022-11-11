package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.core.view.get
import com.proyecto.appejemplomascotas.databinding.ActivityRegistrarMascotaBinding

class RegistrarMascotaActivity: Activity() {

    lateinit var binding: ActivityRegistrarMascotaBinding
    lateinit var btn_registro_mascota:Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_registro_mascota = binding.registarMascota
        btn_registro_mascota.setOnClickListener{
            registrarMascota()
        }
    }

    fun registrarMascota(){
        val nombreMascota:String = binding.nombreMascota.text.toString()
        val tipoMascota:String = binding.tipoMascota.toString()
        val edad:Int = Integer.parseInt(binding.edadMascota.text.toString())
        val tipoBanio:String = binding.tipoBanho.toString()

        var preferences = getSharedPreferences(nombreMascota, Context.MODE_PRIVATE)
        var editar = preferences.edit()

        editar.putString("nombreMascota", nombreMascota)
        editar.putString("tipoMascota", tipoMascota)
        editar.putInt("edad", edad)
        editar.putString("tipoBanio",tipoBanio)

        if(nombreMascota.isNotEmpty() && tipoMascota.isNotEmpty()){
            Toast.makeText(this,"Mascota registrada exitosamente", Toast.LENGTH_SHORT).show()
            editar.apply()
            startActivity(Intent(this,LoginActivity::class.java))
        }else{
            Toast.makeText(this," Favor completar campos obligatorios", Toast.LENGTH_SHORT).show()
        }
    }
}