package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.RelativeLayout
import com.proyecto.appejemplomascotas.databinding.ActivityLoginBinding
import com.proyecto.appejemplomascotas.databinding.ActivityMainBinding

class LoginActivity: Activity() {

    lateinit var binding: ActivityLoginBinding //Manejar los elementos de la vista
    lateinit var btn_registro:Button
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)
        setContentView(binding.root)
        btn_registro = binding.btnRegistro
        btn_registro.setOnClickListener{
            startActivity(Intent(this,RegistrarUsuarioActivity::class.java))
        }
    }
}