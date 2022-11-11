package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.proyecto.appejemplomascotas.databinding.ActivityRegistrarUsuarioBinding

class RegistrarUsuarioActivity: Activity(){

    lateinit var binding: ActivityRegistrarUsuarioBinding
    lateinit var btn_registro:Button

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_registro = binding.btnRegUsuario
        btn_registro.setOnClickListener{
            registrarUsuario()
        }
    }

    fun registrarUsuario(){
        val nombre:String = binding.nombreUsuario.text.toString()
        val apellido:String = binding.apellidoUsuario.text.toString()
        val email:String = binding.email.text.toString()
        val tipoDoc:String = binding.tipoID.text.toString()
        val numDoc:String = binding.numID.text.toString()
        val celular:String = binding.numCel.text.toString()
        val contrasenha:String = binding.registroPassword.text.toString()

        var preferences = getSharedPreferences(numDoc, Context.MODE_PRIVATE)
        var editar = preferences.edit()

        editar.putString("nombre", nombre)
        editar.putString("apellido", apellido)
        editar.putString("email", email)
        editar.putString("contraseña",contrasenha)
        editar.putString("tipoDocumento", tipoDoc)
        editar.putString("numeroDocumento", numDoc)
        editar.putString("celular", celular)

        if(numDoc.isNotEmpty() && contrasenha.isNotEmpty()){
            Toast.makeText(this,"Usuario registrado exitosamente",Toast.LENGTH_SHORT).show()
            editar.apply()
            startActivity(Intent(this,RegistrarMascotaActivity::class.java))
        }else{
            Toast.makeText(this," Favor completar número de identificación y contraseña",Toast.LENGTH_SHORT).show()
        }
    }

}