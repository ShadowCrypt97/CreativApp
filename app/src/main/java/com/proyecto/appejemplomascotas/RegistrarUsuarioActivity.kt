package com.proyecto.appejemplomascotas

import android.app.Activity
import android.os.Bundle
import com.proyecto.appejemplomascotas.databinding.ActivityRegistrarUsuarioBinding

class RegistrarUsuarioActivity: Activity(){

    lateinit var binding: ActivityRegistrarUsuarioBinding

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}