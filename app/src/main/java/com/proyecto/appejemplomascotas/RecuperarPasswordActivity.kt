package com.proyecto.appejemplomascotas

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.proyecto.appejemplomascotas.databinding.ActivityRecoverPasswordBinding

class RecuperarPasswordActivity:Activity() {

    lateinit var binding: ActivityRecoverPasswordBinding
    lateinit var btn_send: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoverPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btn_send = binding.recoverEnviar
        btn_send.setOnClickListener{
            recuperarPassword()
        }
    }

    fun recuperarPassword(){
        val email:String = binding.recoverEmail.text.toString()
        if (email.isNotEmpty())
            Toast.makeText(this,"Recibirás una notificación en tu correo electrónico",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Porfavor ingresa tu correo electrónico",Toast.LENGTH_LONG).show()

    }
}