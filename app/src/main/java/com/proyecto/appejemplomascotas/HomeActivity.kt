package com.proyecto.appejemplomascotas

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.appejemplomascotas.databinding.ActivityHomeBinding
import com.proyecto.appejemplomascotas.databinding.ActivityLoginBinding

class HomeActivity: AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding //Manejar los elementos de la vista

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)
        setContentView(binding.root)
    }
}