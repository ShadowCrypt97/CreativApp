package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.proyecto.appejemplomascotas.databinding.ActivityLoginBinding

class LoginActivity: Activity() {

    lateinit var binding: ActivityLoginBinding //Manejar los elementos de la vista
    lateinit var btn_registro:Button
    lateinit var btn_login:Button
    lateinit var link_recover_pass:TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)
        setContentView(binding.root)
        link_recover_pass = binding.recuperarContrasenha
        btn_registro = binding.btnRegistro
        btn_login = binding.btnLogin
        btn_registro.setOnClickListener{
            startActivity(Intent(this,RegistrarUsuarioActivity::class.java))
        }
        btn_login.setOnClickListener{
            iniciarSesion()
        }
        link_recover_pass.setOnClickListener{
            startActivity(Intent(this,RecuperarPasswordActivity::class.java))
        }

    }

    fun iniciarSesion(){

        val usuario:String = binding.loginNombreUsuario.text.toString()
        val password:String = binding.loginIngresarContrasenha.text.toString()

        val preferences = getSharedPreferences(usuario,Context.MODE_PRIVATE)
        val documentoAlmacenado = preferences.all.get("numeroDocumento")
        val passwordAlmacenada = preferences.getString("contraseña",password).orEmpty()

        if (documentoAlmacenado == usuario && passwordAlmacenada == password)
            Toast.makeText(this,"Bienvenido $usuario", Toast.LENGTH_SHORT).show()
        else
            Toast.makeText(this,"Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
    }
}