package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.proyecto.appejemplomascotas.databinding.ActivityRegistrarUsuarioBinding
import kotlinx.coroutines.launch

class RegistrarUsuarioActivity: AppCompatActivity(){

    lateinit var binding: ActivityRegistrarUsuarioBinding
    lateinit var btn_registro:Button
    lateinit var campoTipoId:Spinner
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val listaTipoDoc = arrayOf("Seleccione tipo de documento", "Cédula", "Tarjeta de identidad", "NIT", "Pasaporte")
        val adaptador:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_items,listaTipoDoc)
        binding = ActivityRegistrarUsuarioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adaptador.setDropDownViewResource(R.layout.spinner_dropdown_item)
        campoTipoId = binding.dropdown
        campoTipoId.adapter = adaptador
        btn_registro = binding.btnRegUsuario
        btn_registro.setOnClickListener{
            registrarUsuario()
        }
    }

    fun registrarUsuario() {
        val nombre:String = binding.nombreUsuario.text.toString()
        val apellido:String = binding.apellidoUsuario.text.toString()
        val email:String = binding.email.text.toString()
        val numDoc:String = binding.numID.text.toString()
        val celular:String = binding.numCel.text.toString()
        val contrasenha:String = binding.registroPassword.text.toString()
        var tipoDoc:String = campoTipoId.selectedItem.toString()
        val room = Room.databaseBuilder(this,bdUsuarios::class.java,"NativAppBD").build()

        campoTipoId.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                if(position>0){
                    tipoDoc = campoTipoId.selectedItem.toString()
                    //editar.putString("tipoDocumento", campoTipoId.selectedItem.toString())
                }else
                    Toast.makeText(this@RegistrarUsuarioActivity,"No has seleccionado el tipo de documento",Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@RegistrarUsuarioActivity,"No has seleccionado el tipo de documento",Toast.LENGTH_SHORT).show()
            }
        }
        val usuario = UsuarioEntidad(email,nombre,apellido, celular,tipoDoc,numDoc,contrasenha)

        lifecycleScope.launch{
            room.daoUsuario().almacenarUsuario(usuario)
            var lista = room.daoUsuario().getUsuarios()
            for (user in lista){
                println("############ ---- ${user.email} --- ${user.nombre} ----${user.tipoDoc} ${user.numeroDoc} ---- ##################")
            }
        }

        /*
        val preferences = getSharedPreferences(email, Context.MODE_PRIVATE)
        val editar = preferences.edit()

        editar.putString("nombre", nombre)
        editar.putString("apellido", apellido)
        editar.putString("email", email)
        editar.putString("contraseña",contrasenha)
        editar.putString("numeroDocumento", numDoc)
        editar.putString("celular", celular)
        */


        if(email.isNotEmpty() && contrasenha.isNotEmpty()){
            Toast.makeText(this,"Usuario registrado exitosamente",Toast.LENGTH_SHORT).show()
            //editar.apply()
            startActivity(Intent(this,LoginActivity::class.java))
        }else{
            Toast.makeText(this," Favor completar número de identificación y contraseña",Toast.LENGTH_SHORT).show()
        }
    }

}