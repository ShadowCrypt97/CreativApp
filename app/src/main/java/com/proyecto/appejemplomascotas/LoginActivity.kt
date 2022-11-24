package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.proyecto.appejemplomascotas.databinding.ActivityLoginBinding

class LoginActivity: Activity() {

    lateinit var binding: ActivityLoginBinding //Manejar los elementos de la vista
    lateinit var btn_registro:Button
    lateinit var btn_login:Button
    lateinit var link_recover_pass:TextView
    lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_login)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth

        link_recover_pass = binding.recuperarContrasenha
        btn_registro = binding.btnRegistro
        btn_login = binding.btnLogin
        btn_registro.setOnClickListener{
            startActivity(Intent(this,RegistrarUsuarioActivity::class.java))
        }
        btn_login.setOnClickListener{
            //validarMemoria()
            validarFirebase()
        }
        link_recover_pass.setOnClickListener{
            startActivity(Intent(this,RecuperarPasswordActivity::class.java))
        }

    }

    fun validarMemoria(){

        val usuario:String = binding.loginNombreUsuario.text.toString()
        val password:String = binding.loginIngresarContrasenha.text.toString()

        val preferences = getSharedPreferences(usuario,Context.MODE_PRIVATE)
        val emailAlmacenado = preferences.getString("email",null).orEmpty()
        val passwordAlmacenada = preferences.getString("contraseña",null).orEmpty()

        if (emailAlmacenado == usuario && passwordAlmacenada == password) {
            var intent = Intent(this,HomeActivity::class.java)
            intent.putExtra("email",usuario)
            startActivity(intent)
        }
        else
            Toast.makeText(this,"Usuario y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
    }
    fun validarFirebase(){
        val correo = binding.loginNombreUsuario.text.toString()
        val password = binding.loginIngresarContrasenha.text.toString()
        if(correo.isEmpty()){
            Toast.makeText(this,"Ingrese su correo electrónico", Toast.LENGTH_SHORT).show()
        }else if(password.isEmpty()){
            Toast.makeText(this,"Ingrese su contraseña", Toast.LENGTH_SHORT).show()
        }else{
            firebaseAuth.signInWithEmailAndPassword(correo,password).addOnCompleteListener(this){
                task ->
                    if (task.isSuccessful){
                        val user = firebaseAuth.currentUser
                        if(user != null){
                            Toast.makeText(this,"Bienvenido ${user.email}", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this,HomeActivity::class.java))
                        }
                    }else{
                        Toast.makeText(this,"Correo y/o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }
}