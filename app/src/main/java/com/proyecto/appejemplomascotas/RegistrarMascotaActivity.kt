package com.proyecto.appejemplomascotas

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.proyecto.appejemplomascotas.databinding.ActivityRegistrarMascotaBinding
import kotlinx.coroutines.launch

class RegistrarMascotaActivity: AppCompatActivity() {

    lateinit var binding: ActivityRegistrarMascotaBinding
    lateinit var btn_registro_mascota:Button
    lateinit var campoTipoMascota: Spinner
    lateinit var campoTipoBanho:Spinner
    lateinit var firebaseAuth: FirebaseAuth
    var banderaTipoMascota:Boolean = true
    var banderaTipoBanho:Boolean = true


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val listaTipoMascota = arrayOf("Seleccione tipo de mascota", "Perro", "Gato")
        val listaTipoBanho = arrayOf("Seleccione tipo de baño", "Medicado", "Completo", "Antipulgas")
        val adaptadorMascota: ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_items,listaTipoMascota)
        val adaptadorTipoBanho:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_items,listaTipoBanho)
        firebaseAuth = Firebase.auth
        val email = firebaseAuth.currentUser?.email.toString()
        //val email = intent.getStringExtra("email")
        Toast.makeText(this,"Bienvenido $email", Toast.LENGTH_SHORT).show()

        binding = ActivityRegistrarMascotaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        adaptadorMascota.setDropDownViewResource(R.layout.spinner_dropdown_item)
        adaptadorTipoBanho.setDropDownViewResource(R.layout.spinner_dropdown_item)
        campoTipoMascota = binding.dropdownTipoMascota
        campoTipoMascota.adapter = adaptadorMascota
        campoTipoBanho = binding.dropdownTipoBanho
        campoTipoBanho.adapter = adaptadorTipoBanho
        btn_registro_mascota = binding.registarMascota

        btn_registro_mascota.setOnClickListener{
            campoTipoMascota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                    banderaTipoMascota = position>0
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    banderaTipoMascota=false
                }
            }
            campoTipoBanho.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
                    banderaTipoBanho = position>0
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    banderaTipoBanho = false
                }
            }
            registrarMascotaRoom(banderaTipoMascota,banderaTipoBanho,email)
        }
    }



    fun registrarMascotaRoom(b1:Boolean, b2:Boolean, email: String){
        val room = Room.databaseBuilder(this,bdUsuarios::class.java,"NativAppBDPets").build()
        val id:String =  email+"_"+binding.nombreMascota.text.toString()
        val nombreMascota:String = binding.nombreMascota.text.toString()
        val edad:String = binding.edadMascota.text.toString()
        val tipoMascota:String = campoTipoMascota.selectedItem.toString()
        val fotoUrl:Int = R.drawable.mascotas
        val mascota = MascotaEntity(id,nombreMascota,tipoMascota,edad,fotoUrl)

        if(nombreMascota.isNotEmpty() && b1 && b2){
            lifecycleScope.launch{
                room.daoMascota().almacenarMascota(mascota)
                val lista = room.daoMascota().getMascotas()
                for (pet in lista){
                    println("############ ---- ${pet.id} --- ${pet.nombre} ----${pet.tipoMascota} ${pet.edad} ---- ${pet.fotoUrl} ##################")
                }
            }
            Toast.makeText(this,"Mascota registrada exitosamente", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,HomeActivity::class.java))
        }else{
            Toast.makeText(this," Favor completar campos obligatorios", Toast.LENGTH_SHORT).show()
        }

    }
    fun registrarMascota(b1:Boolean, b2:Boolean, email:String){
        val nombreMascota:String = binding.nombreMascota.text.toString()
        val edad:Int = Integer.parseInt(binding.edadMascota.text.toString())

        val preferences = getSharedPreferences("${nombreMascota}_${email}", Context.MODE_PRIVATE)
        val editar = preferences.edit()

        editar.putString("nombreMascota", nombreMascota)
        editar.putString("tipoMascota", campoTipoMascota.selectedItem.toString())
        editar.putString("tipoBanho", campoTipoBanho.selectedItem.toString())
        editar.putInt("edad", edad)


        if(nombreMascota.isNotEmpty() && b1 && b2){
            Toast.makeText(this,"Mascota registrada exitosamente", Toast.LENGTH_SHORT).show()
            editar.apply()
            startActivity(Intent(this,HomeActivity::class.java))
        }else{
            Toast.makeText(this," Favor completar campos obligatorios", Toast.LENGTH_SHORT).show()
        }
    }
}