package com.proyecto.appejemplomascotas

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.proyecto.appejemplomascotas.databinding.ActivityRegistrarMascotaBinding

class RegistrarMascotaActivity: Activity() {

    lateinit var binding: ActivityRegistrarMascotaBinding
    lateinit var btn_registro_mascota:Button
    lateinit var campoTipoMascota: Spinner
    lateinit var campoTipoBanho:Spinner
    var banderaTipoMascota:Boolean = true
    var banderaTipoBanho:Boolean = true


    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        val listaTipoMascota = arrayOf("Seleccione tipo de mascota", "Perro", "Gato")
        val listaTipoBanho = arrayOf("Seleccione tipo de baño", "Medicado", "Completo", "Antipulgas")
        val adaptadorMascota: ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_items,listaTipoMascota)
        val adaptadorTipoBanho:ArrayAdapter<String> = ArrayAdapter(this,R.layout.spinner_items,listaTipoBanho)
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
            registrarMascota(banderaTipoMascota,banderaTipoBanho)
        }
    }

    fun registrarMascota(b1:Boolean, b2:Boolean){
        val nombreMascota:String = binding.nombreMascota.text.toString()
        val edad:Int = Integer.parseInt(binding.edadMascota.text.toString())

        val preferences = getSharedPreferences(nombreMascota, Context.MODE_PRIVATE)
        val editar = preferences.edit()

        editar.putString("nombreMascota", nombreMascota)
        editar.putString("tipoMascota", campoTipoMascota.selectedItem.toString())
        editar.putString("tipoBanho", campoTipoMascota.selectedItem.toString())
        editar.putInt("edad", edad)


        if(nombreMascota.isNotEmpty() && b1 && b2){
            Toast.makeText(this,"Mascota registrada exitosamente", Toast.LENGTH_SHORT).show()
            editar.apply()
            startActivity(Intent(this,LoginActivity::class.java))
        }else{
            Toast.makeText(this," Favor completar campos obligatorios", Toast.LENGTH_SHORT).show()

            binding.tomarfoto.setOnClickListener {

        }
    }
    val abrirCamara =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { }
}