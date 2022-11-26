package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.proyecto.appejemplomascotas.databinding.ActivityRecoverPasswordBinding

class RecuperarPasswordActivity:Activity() {

    lateinit var binding: ActivityRecoverPasswordBinding
    lateinit var btn_send: Button
    lateinit var firebaseAuth: FirebaseAuth

    var email: String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecoverPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = Firebase.auth
        btn_send = binding.recoverEnviar
        btn_send.setOnClickListener{
            email = binding.recoverEmail.text.toString()
            //recuperarPassword()
            recuperarPasswordFirebase(email!!)
        }
    }

    fun recuperarPassword(){
        val email:String = binding.recoverEmail.text.toString()
        if (email.isNotEmpty())
            Toast.makeText(this,"Recibirás una notificación en tu correo electrónico",Toast.LENGTH_LONG).show()
        else
            Toast.makeText(this,"Porfavor ingresa tu correo electrónico",Toast.LENGTH_LONG).show()

    }
    fun recuperarPasswordFirebase(email:String){
        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener{
            task ->
                if(task.isSuccessful){
                    Toast.makeText(this,"Recibirás una notificación en tu correo electrónico", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }else{
                    Toast.makeText(this,"Error al enviar correo electrónico", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this,LoginActivity::class.java))
                }
        }
    }
}